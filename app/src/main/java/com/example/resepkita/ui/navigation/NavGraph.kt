package com.example.resepkita.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.resepkita.ui.RecipeViewModel
import com.example.resepkita.ui.screens.AddRecipeScreen
import com.example.resepkita.ui.screens.CookRecipeScreen
import com.example.resepkita.ui.screens.HomeScreen
import com.example.resepkita.ui.screens.LoginScreen
import com.example.resepkita.ui.screens.OnboardingScreen
import com.example.resepkita.ui.screens.ProfileScreen
import com.example.resepkita.ui.screens.RecipeDetailScreen
import com.example.resepkita.ui.screens.SavedScreen
import com.example.resepkita.ui.screens.SearchScreen
import com.example.resepkita.ui.screens.SignUpScreen

@Composable
fun NavGraph(viewModel: RecipeViewModel = viewModel()) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""

    // Determine if we should show bottom bar
    val showBottomBar = currentRoute in listOf("home", "search", "saved", "profile") && viewModel.isSignedIn

    // Start destination based on onboarding and auth state
    val startDestination = when {
        !viewModel.hasCompletedOnboarding -> "onboarding"
        viewModel.isSignedIn -> "home"
        else -> "login"
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(
                    currentRoute = currentRoute,
                    onNavigate = { route ->
                        if (route == "add") {
                            navController.navigate("add")
                        } else {
                            navController.navigate(route) {
                                popUpTo("home") { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("onboarding") {
                OnboardingScreen(
                    onFinish = {
                        viewModel.completeOnboarding()
                        navController.navigate("login") {
                            popUpTo("onboarding") { inclusive = true }
                        }
                    }
                )
            }

            // Auth screens
            composable("login") {
                LoginScreen(
                    onSignIn = { email, password ->
                        viewModel.signIn(email, password)
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    onNavigateToSignUp = {
                        navController.navigate("signup")
                    }
                )
            }

            composable("signup") {
                SignUpScreen(
                    onSignUp = { name, email, password ->
                        viewModel.signUp(name, email, password)
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    onNavigateToLogin = {
                        navController.popBackStack()
                    }
                )
            }

            // Main tabs
            composable("home") {
                HomeScreen(
                    userName = viewModel.currentUser.name,
                    userEmoji = viewModel.currentUser.avatarEmoji,
                    searchQuery = viewModel.searchQuery,
                    onSearchQueryChange = { viewModel.updateSearchQuery(it) },
                    categories = viewModel.categories,
                    selectedCategory = viewModel.selectedCategory,
                    onCategorySelected = { viewModel.selectCategory(it) },
                    filteredRecipes = viewModel.getFilteredRecipes(),
                    featuredRecipe = viewModel.getFeaturedRecipe(),
                    onRecipeClick = { id -> navController.navigate("detail/$id") },
                    onFavoriteClick = { id -> viewModel.toggleFavorite(id) }
                )
            }

            composable("search") {
                SearchScreen(
                    searchQuery = viewModel.searchQuery,
                    onSearchQueryChange = { viewModel.updateSearchQuery(it) },
                    results = viewModel.getSearchResults(),
                    onRecipeClick = { id -> navController.navigate("detail/$id") },
                    onFavoriteClick = { id -> viewModel.toggleFavorite(id) }
                )
            }

            composable("add") {
                AddRecipeScreen(
                    onBack = { navController.popBackStack() },
                    onSave = { recipe ->
                        viewModel.addRecipe(recipe)
                        navController.popBackStack()
                    }
                )
            }

            composable(
                "edit/{recipeId}",
                arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
            ) { backStackEntry ->
                val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: return@composable
                val recipe = viewModel.getRecipeById(recipeId) ?: return@composable
                AddRecipeScreen(
                    recipeToEdit = recipe,
                    onBack = { navController.popBackStack() },
                    onSave = { updatedRecipe ->
                        viewModel.updateRecipe(updatedRecipe)
                        navController.popBackStack()
                    }
                )
            }

            composable(
                "cook/{recipeId}",
                arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
            ) { backStackEntry ->
                val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: return@composable
                val recipe = viewModel.getRecipeById(recipeId) ?: return@composable
                CookRecipeScreen(
                    recipe = recipe,
                    onBack = { navController.popBackStack() }
                )
            }

            composable("saved") {
                SavedScreen(
                    savedRecipes = viewModel.getSavedRecipes(),
                    onRecipeClick = { id -> navController.navigate("detail/$id") },
                    onFavoriteClick = { id -> viewModel.toggleFavorite(id) }
                )
            }

            composable("profile") {
                ProfileScreen(
                    userName = viewModel.currentUser.name,
                    userEmail = viewModel.currentUser.email,
                    userEmoji = viewModel.currentUser.avatarEmoji,
                    recipeCount = viewModel.getRecipeCount(),
                    savedCount = viewModel.getSavedCount(),
                    isDarkTheme = viewModel.isDarkTheme,
                    onDarkThemeChange = { viewModel.updateDarkTheme(it) },
                    onSignOut = {
                        viewModel.signOut()
                        navController.navigate("login") {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }

            // Recipe detail
            composable(
                "detail/{recipeId}",
                arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
            ) { backStackEntry ->
                val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: return@composable
                val recipe = viewModel.getRecipeById(recipeId) ?: return@composable
                RecipeDetailScreen(
                    recipe = recipe,
                    onBack = { navController.popBackStack() },
                    onFavoriteClick = { id -> viewModel.toggleFavorite(id) },
                    onEditClick = { id -> navController.navigate("edit/$id") },
                    onDeleteClick = { id ->
                        viewModel.deleteRecipe(id)
                        navController.popBackStack()
                    },
                    onCookClick = { id -> navController.navigate("cook/$id") }
                )
            }
        }
    }
}
