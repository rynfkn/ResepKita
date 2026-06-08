package com.example.resepkita.ui

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.resepkita.data.SampleData
import com.example.resepkita.data.model.Recipe
import com.example.resepkita.data.model.User

class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    val recipes = mutableStateListOf<Recipe>().apply {
        addAll(SampleData.recipes)
    }

    var isSignedIn by mutableStateOf(false)
        private set

    var currentUser by mutableStateOf(
        User(
            name = prefs.getString(KEY_USER_NAME, User().name) ?: User().name,
            email = prefs.getString(KEY_USER_EMAIL, User().email) ?: User().email,
            avatarEmoji = prefs.getString(KEY_USER_AVATAR, User().avatarEmoji) ?: User().avatarEmoji
        )
    )
        private set

    var selectedCategory by mutableStateOf("All")
        private set

    var searchQuery by mutableStateOf("")
        private set

    var searchCategory by mutableStateOf("All")
        private set

    var searchDifficulty by mutableStateOf("Any")
        private set

    var searchMaxTimeMinutes by mutableStateOf<Int?>(null)
        private set

    var searchMinRating by mutableStateOf(0f)
        private set

    var searchFavoritesOnly by mutableStateOf(false)
        private set

    var isDarkTheme by mutableStateOf(prefs.getBoolean(KEY_DARK_THEME, false))
        private set

    var hasCompletedOnboarding by mutableStateOf(prefs.getBoolean(KEY_ONBOARDING_COMPLETE, false))
        private set

    var notificationsEnabled by mutableStateOf(prefs.getBoolean(KEY_NOTIFICATIONS_ENABLED, true))
        private set

    private var nextId by mutableIntStateOf(100)

    val categories = listOf("All", "Breakfast", "Lunch", "Dinner", "Dessert", "Snacks")
    val difficulties = listOf("Any", "Easy", "Medium", "Hard")

    fun signIn(email: String, password: String) {
        currentUser = currentUser.copy(email = email)
        saveUser()
        isSignedIn = true
    }

    fun signUp(name: String, email: String, password: String) {
        currentUser = currentUser.copy(name = name, email = email)
        saveUser()
        isSignedIn = true
    }

    fun signOut() {
        isSignedIn = false
    }

    fun selectCategory(category: String) {
        selectedCategory = category
    }

    fun updateSearchQuery(query: String) {
        searchQuery = query
    }

    fun updateSearchCategory(category: String) {
        searchCategory = category
    }

    fun updateSearchDifficulty(difficulty: String) {
        searchDifficulty = difficulty
    }

    fun updateSearchMaxTime(minutes: Int?) {
        searchMaxTimeMinutes = minutes
    }

    fun updateSearchMinRating(rating: Float) {
        searchMinRating = rating
    }

    fun updateSearchFavoritesOnly(enabled: Boolean) {
        searchFavoritesOnly = enabled
    }

    fun clearSearchFilters() {
        searchCategory = "All"
        searchDifficulty = "Any"
        searchMaxTimeMinutes = null
        searchMinRating = 0f
        searchFavoritesOnly = false
    }

    fun updateDarkTheme(enabled: Boolean) {
        isDarkTheme = enabled
        prefs.edit().putBoolean(KEY_DARK_THEME, enabled).apply()
    }

    fun completeOnboarding() {
        hasCompletedOnboarding = true
        prefs.edit().putBoolean(KEY_ONBOARDING_COMPLETE, true).apply()
    }

    fun updateNotificationsEnabled(enabled: Boolean) {
        notificationsEnabled = enabled
        prefs.edit().putBoolean(KEY_NOTIFICATIONS_ENABLED, enabled).apply()
    }

    fun updateProfile(name: String, avatarEmoji: String) {
        currentUser = currentUser.copy(
            name = name.ifBlank { currentUser.name },
            avatarEmoji = avatarEmoji.ifBlank { currentUser.avatarEmoji }
        )
        saveUser()
    }

    fun getFilteredRecipes(): List<Recipe> {
        var filtered = recipes.toList()
        if (selectedCategory != "All") {
            filtered = filtered.filter { it.category == selectedCategory }
        }
        if (searchQuery.isNotBlank()) {
            filtered = filtered.filter {
                it.title.contains(searchQuery, ignoreCase = true) ||
                it.description.contains(searchQuery, ignoreCase = true) ||
                it.ingredients.any { ing -> ing.name.contains(searchQuery, ignoreCase = true) }
            }
        }
        return filtered
    }

    fun getSearchResults(): List<Recipe> {
        return recipes.filter { recipe ->
            val matchesQuery = searchQuery.isBlank() ||
                recipe.title.contains(searchQuery, ignoreCase = true) ||
                recipe.description.contains(searchQuery, ignoreCase = true) ||
                recipe.category.contains(searchQuery, ignoreCase = true) ||
                recipe.ingredients.any { ing -> ing.name.contains(searchQuery, ignoreCase = true) }

            val matchesCategory = searchCategory == "All" || recipe.category == searchCategory
            val matchesDifficulty = searchDifficulty == "Any" || recipe.difficulty == searchDifficulty
            val matchesTime = searchMaxTimeMinutes == null || recipe.timeMinutes <= searchMaxTimeMinutes!!
            val matchesRating = recipe.rating >= searchMinRating
            val matchesFavorite = !searchFavoritesOnly || recipe.isFavorite

            matchesQuery && matchesCategory && matchesDifficulty && matchesTime && matchesRating && matchesFavorite
        }
    }

    fun getSavedRecipes(): List<Recipe> {
        return recipes.filter { it.isFavorite }
    }

    fun getFeaturedRecipe(): Recipe? {
        return recipes.maxByOrNull { it.rating }
    }

    fun getRecipeById(id: Int): Recipe? {
        return recipes.find { it.id == id }
    }

    fun toggleFavorite(recipeId: Int) {
        val index = recipes.indexOfFirst { it.id == recipeId }
        if (index >= 0) {
            recipes[index] = recipes[index].copy(isFavorite = !recipes[index].isFavorite)
        }
    }

    fun addRecipe(recipe: Recipe) {
        val newRecipe = recipe.copy(id = nextId++)
        recipes.add(0, newRecipe)
    }

    fun updateRecipe(recipe: Recipe) {
        val index = recipes.indexOfFirst { it.id == recipe.id }
        if (index >= 0) {
            recipes[index] = recipe
        }
    }

    fun deleteRecipe(recipeId: Int) {
        recipes.removeAll { it.id == recipeId }
    }

    fun getRecipeCount(): Int = recipes.size

    fun getSavedCount(): Int = recipes.count { it.isFavorite }

    fun getAverageRating(): Float = recipes.map { it.rating }.average().toFloat()

    fun getTotalCookTimeMinutes(): Int = recipes.sumOf { it.timeMinutes }

    private fun saveUser() {
        prefs.edit()
            .putString(KEY_USER_NAME, currentUser.name)
            .putString(KEY_USER_EMAIL, currentUser.email)
            .putString(KEY_USER_AVATAR, currentUser.avatarEmoji)
            .apply()
    }

    private companion object {
        const val PREFS_NAME = "resepkita_settings"
        const val KEY_DARK_THEME = "dark_theme"
        const val KEY_ONBOARDING_COMPLETE = "onboarding_complete"
        const val KEY_NOTIFICATIONS_ENABLED = "notifications_enabled"
        const val KEY_USER_NAME = "user_name"
        const val KEY_USER_EMAIL = "user_email"
        const val KEY_USER_AVATAR = "user_avatar"
    }
}
