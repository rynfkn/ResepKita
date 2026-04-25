package com.example.resepkita.data.model

data class Ingredient(
    val amount: String = "",
    val name: String = ""
)

data class Recipe(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val category: String = "Breakfast",
    val difficulty: String = "Easy",
    val timeMinutes: Int = 0,
    val servings: Int = 1,
    val rating: Float = 0f,
    val imageResId: Int = 0,
    val ingredients: List<Ingredient> = emptyList(),
    val steps: List<String> = emptyList(),
    val isFavorite: Boolean = false,
    val tags: List<String> = emptyList()
)
