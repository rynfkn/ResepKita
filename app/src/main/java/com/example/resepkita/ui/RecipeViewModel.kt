package com.example.resepkita.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.resepkita.data.SampleData
import com.example.resepkita.data.model.Recipe
import com.example.resepkita.data.model.User

class RecipeViewModel : ViewModel() {

    val recipes = mutableStateListOf<Recipe>().apply {
        addAll(SampleData.recipes)
    }

    var isSignedIn by mutableStateOf(false)
        private set

    var currentUser by mutableStateOf(User())
        private set

    var selectedCategory by mutableStateOf("All")
        private set

    var searchQuery by mutableStateOf("")
        private set

    var isDarkTheme by mutableStateOf(false)
        private set

    var hasCompletedOnboarding by mutableStateOf(false)
        private set

    private var nextId by mutableIntStateOf(100)

    val categories = listOf("All", "Breakfast", "Lunch", "Dinner", "Dessert", "Snacks")

    fun signIn(email: String, password: String) {
        currentUser = User(name = "Alex", email = email, avatarEmoji = "🤩")
        isSignedIn = true
    }

    fun signUp(name: String, email: String, password: String) {
        currentUser = User(name = name, email = email, avatarEmoji = "🤩")
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

    fun updateDarkTheme(enabled: Boolean) {
        isDarkTheme = enabled
    }

    fun completeOnboarding() {
        hasCompletedOnboarding = true
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
        if (searchQuery.isBlank()) return recipes.toList()
        return recipes.filter {
            it.title.contains(searchQuery, ignoreCase = true) ||
            it.description.contains(searchQuery, ignoreCase = true) ||
            it.category.contains(searchQuery, ignoreCase = true) ||
            it.ingredients.any { ing -> ing.name.contains(searchQuery, ignoreCase = true) }
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

    fun deleteRecipe(recipeId: Int) {
        recipes.removeAll { it.id == recipeId }
    }

    fun getRecipeCount(): Int = recipes.size

    fun getSavedCount(): Int = recipes.count { it.isFavorite }
}
