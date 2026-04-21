package com.example.resepkita.data

import com.example.resepkita.data.model.Ingredient
import com.example.resepkita.data.model.Recipe

object SampleData {
    val recipes = listOf(
        Recipe(
            id = 1,
            title = "Avocado Toast",
            description = "Creamy avocado on toasted sourdough with cherry tomatoes and microgreens",
            category = "Breakfast",
            difficulty = "Easy",
            timeMinutes = 10,
            servings = 2,
            rating = 4.8f,
            imageEmoji = "🥑",
            isFavorite = true,
            tags = listOf("vegetarian", "quick"),
            ingredients = listOf(
                Ingredient("2 slices", "Sourdough bread"),
                Ingredient("1", "Ripe avocado"),
                Ingredient("6", "Cherry tomatoes"),
                Ingredient("1 tbsp", "Olive oil"),
                Ingredient("Pinch", "Salt & pepper"),
                Ingredient("Handful", "Microgreens")
            ),
            steps = listOf(
                "Toast the sourdough bread slices until golden and crispy.",
                "Cut the avocado in half, remove the pit, and scoop the flesh into a bowl.",
                "Mash the avocado with a fork, season with salt and pepper.",
                "Spread the mashed avocado evenly on each toast.",
                "Halve the cherry tomatoes and arrange on top.",
                "Drizzle with olive oil and garnish with microgreens."
            )
        ),
        Recipe(
            id = 2,
            title = "Spaghetti Carbonara",
            description = "Classic Italian pasta with eggs, cheese, pancetta, and black pepper",
            category = "Dinner",
            difficulty = "Medium",
            timeMinutes = 25,
            servings = 4,
            rating = 4.9f,
            imageEmoji = "🍝",
            isFavorite = false,
            tags = listOf("italian", "classic"),
            ingredients = listOf(
                Ingredient("400g", "Spaghetti"),
                Ingredient("200g", "Pancetta or guanciale"),
                Ingredient("4", "Egg yolks"),
                Ingredient("100g", "Pecorino Romano"),
                Ingredient("To taste", "Black pepper"),
                Ingredient("1 tbsp", "Olive oil")
            ),
            steps = listOf(
                "Bring a large pot of salted water to boil and cook spaghetti al dente.",
                "While pasta cooks, dice the pancetta and fry in olive oil until crispy.",
                "In a bowl, whisk together egg yolks, grated pecorino, and generous black pepper.",
                "Reserve 1 cup of pasta water, then drain spaghetti.",
                "Toss hot pasta with pancetta (heat off), then quickly mix in egg mixture.",
                "Add pasta water as needed for creamy consistency. Serve immediately."
            )
        ),
        Recipe(
            id = 3,
            title = "Mango Smoothie Bowl",
            description = "Tropical smoothie bowl topped with granola, coconut, and fresh fruits",
            category = "Breakfast",
            difficulty = "Easy",
            timeMinutes = 8,
            servings = 1,
            rating = 4.7f,
            imageEmoji = "🥭",
            isFavorite = true,
            tags = listOf("healthy", "vegan"),
            ingredients = listOf(
                Ingredient("1 cup", "Frozen mango chunks"),
                Ingredient("1/2", "Banana"),
                Ingredient("1/4 cup", "Coconut milk"),
                Ingredient("2 tbsp", "Granola"),
                Ingredient("1 tbsp", "Shredded coconut"),
                Ingredient("5", "Fresh blueberries")
            ),
            steps = listOf(
                "Blend frozen mango, banana, and coconut milk until thick and smooth.",
                "Pour into a bowl.",
                "Top with granola, shredded coconut, and fresh blueberries.",
                "Serve immediately and enjoy!"
            )
        ),
        Recipe(
            id = 4,
            title = "Thai Green Curry",
            description = "Aromatic and creamy Thai green curry with vegetables and jasmine rice",
            category = "Dinner",
            difficulty = "Medium",
            timeMinutes = 35,
            servings = 4,
            rating = 4.6f,
            imageEmoji = "🍛",
            isFavorite = false,
            tags = listOf("thai", "spicy"),
            ingredients = listOf(
                Ingredient("2 tbsp", "Green curry paste"),
                Ingredient("400ml", "Coconut milk"),
                Ingredient("300g", "Chicken or tofu"),
                Ingredient("1 cup", "Thai basil leaves"),
                Ingredient("2", "Kaffir lime leaves"),
                Ingredient("1 tbsp", "Fish sauce"),
                Ingredient("1 cup", "Mixed vegetables"),
                Ingredient("2 cups", "Jasmine rice")
            ),
            steps = listOf(
                "Cook jasmine rice according to package directions.",
                "Heat a tablespoon of coconut milk in a wok until oil separates.",
                "Add green curry paste and stir-fry for 1 minute until fragrant.",
                "Add chicken/tofu and cook until just done.",
                "Pour in remaining coconut milk, add vegetables and lime leaves.",
                "Simmer for 10 minutes, season with fish sauce.",
                "Stir in Thai basil, serve over jasmine rice."
            )
        ),
        Recipe(
            id = 5,
            title = "Chocolate Lava Cake",
            description = "Decadent individual chocolate cakes with a molten center",
            category = "Dessert",
            difficulty = "Hard",
            timeMinutes = 30,
            servings = 4,
            rating = 4.9f,
            imageEmoji = "🍫",
            isFavorite = false,
            tags = listOf("dessert", "chocolate"),
            ingredients = listOf(
                Ingredient("200g", "Dark chocolate"),
                Ingredient("100g", "Butter"),
                Ingredient("3", "Eggs"),
                Ingredient("100g", "Sugar"),
                Ingredient("50g", "Flour"),
                Ingredient("1 tsp", "Vanilla extract")
            ),
            steps = listOf(
                "Preheat oven to 220°C (425°F). Grease 4 ramekins with butter and dust with cocoa.",
                "Melt chocolate and butter together in a double boiler.",
                "Whisk eggs and sugar until pale and thick.",
                "Fold melted chocolate into egg mixture, then fold in flour and vanilla.",
                "Divide batter among ramekins. Bake for 12-14 minutes.",
                "Let cool 1 minute, run a knife around edges, and invert onto plates."
            )
        ),
        Recipe(
            id = 6,
            title = "Caesar Salad",
            description = "Crisp romaine with homemade dressing, croutons, and parmesan",
            category = "Lunch",
            difficulty = "Easy",
            timeMinutes = 15,
            servings = 2,
            rating = 4.5f,
            imageEmoji = "🥗",
            isFavorite = false,
            tags = listOf("salad", "quick"),
            ingredients = listOf(
                Ingredient("1 head", "Romaine lettuce"),
                Ingredient("1/2 cup", "Parmesan cheese"),
                Ingredient("1 cup", "Croutons"),
                Ingredient("3 tbsp", "Caesar dressing"),
                Ingredient("1", "Lemon"),
                Ingredient("2 fillets", "Anchovy (optional)")
            ),
            steps = listOf(
                "Wash and chop romaine lettuce into bite-sized pieces.",
                "Make dressing: whisk together olive oil, lemon juice, anchovy, and garlic.",
                "Toss lettuce with dressing until evenly coated.",
                "Add croutons and toss gently.",
                "Top with shaved parmesan cheese.",
                "Serve immediately with extra lemon wedges."
            )
        )
    )
}
