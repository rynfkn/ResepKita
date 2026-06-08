package com.example.resepkita.data

import com.example.resepkita.data.model.Ingredient
import com.example.resepkita.data.model.Recipe
import com.example.resepkita.R

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
            imageResId = R.drawable.avocado_toast,
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
            imageResId = R.drawable.spaghetti_carbonara,
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
            imageResId = R.drawable.mango_smoothie_bowl,
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
            imageResId = R.drawable.thai_green_curry,
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
            imageResId = R.drawable.choco_lava_cake,
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
            imageResId = R.drawable.caesar_salad,
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
        ),
        Recipe(
            id = 7,
            title = "Chicken Satay Rice Bowl",
            description = "Grilled chicken over jasmine rice with peanut sauce, cucumber, carrots, herbs, and lime",
            category = "Lunch",
            difficulty = "Medium",
            timeMinutes = 28,
            servings = 3,
            rating = 4.8f,
            imageResId = R.drawable.chicken_satay_rice_bowl,
            isFavorite = false,
            tags = listOf("asian", "protein"),
            ingredients = listOf(
                Ingredient("500g", "Chicken breast"),
                Ingredient("2 cups", "Cooked jasmine rice"),
                Ingredient("1/3 cup", "Peanut sauce"),
                Ingredient("1", "Cucumber"),
                Ingredient("1", "Carrot"),
                Ingredient("1", "Lime"),
                Ingredient("Handful", "Cilantro"),
                Ingredient("2 tbsp", "Crushed peanuts")
            ),
            steps = listOf(
                "Slice chicken into strips and season with salt, pepper, and a spoonful of peanut sauce.",
                "Grill or sear chicken until cooked through and lightly charred.",
                "Slice cucumber and julienne the carrot.",
                "Divide warm jasmine rice between bowls.",
                "Top with chicken, vegetables, cilantro, and crushed peanuts.",
                "Drizzle with peanut sauce and serve with lime wedges."
            )
        ),
        Recipe(
            id = 8,
            title = "Shakshuka Skillet",
            description = "Eggs simmered in a spiced tomato and pepper sauce with feta and toasted bread",
            category = "Breakfast",
            difficulty = "Medium",
            timeMinutes = 25,
            servings = 3,
            rating = 4.7f,
            imageResId = R.drawable.shakshuka_skillet,
            isFavorite = true,
            tags = listOf("brunch", "spiced"),
            ingredients = listOf(
                Ingredient("4", "Eggs"),
                Ingredient("1 can", "Crushed tomatoes"),
                Ingredient("1", "Red bell pepper"),
                Ingredient("1/2", "Onion"),
                Ingredient("2 cloves", "Garlic"),
                Ingredient("1 tsp", "Cumin"),
                Ingredient("1/3 cup", "Feta cheese"),
                Ingredient("To serve", "Toasted bread")
            ),
            steps = listOf(
                "Saute onion and bell pepper in olive oil until softened.",
                "Add garlic, cumin, salt, and pepper; cook until fragrant.",
                "Pour in crushed tomatoes and simmer until thickened.",
                "Make small wells in the sauce and crack in the eggs.",
                "Cover and cook until egg whites are set and yolks are still soft.",
                "Top with feta and herbs, then serve with toasted bread."
            )
        ),
        Recipe(
            id = 9,
            title = "Salmon Quinoa Salad",
            description = "Seared salmon over quinoa, greens, avocado, tomatoes, cucumber, lemon, and dill",
            category = "Lunch",
            difficulty = "Easy",
            timeMinutes = 22,
            servings = 2,
            rating = 4.6f,
            imageResId = R.drawable.salmon_quinoa_salad,
            isFavorite = false,
            tags = listOf("healthy", "seafood"),
            ingredients = listOf(
                Ingredient("2 fillets", "Salmon"),
                Ingredient("1 cup", "Cooked quinoa"),
                Ingredient("2 cups", "Mixed greens"),
                Ingredient("1/2", "Avocado"),
                Ingredient("1/2 cup", "Cherry tomatoes"),
                Ingredient("1/2", "Cucumber"),
                Ingredient("1", "Lemon"),
                Ingredient("1 tbsp", "Fresh dill")
            ),
            steps = listOf(
                "Season salmon with salt, pepper, lemon zest, and a little olive oil.",
                "Sear salmon skin-side down until crisp, then flip and cook through.",
                "Toss quinoa, greens, cucumber, tomatoes, avocado, and dill in a bowl.",
                "Whisk lemon juice with olive oil, salt, and pepper.",
                "Place salmon on top of the salad.",
                "Drizzle with lemon dressing and serve immediately."
            )
        ),
        Recipe(
            id = 10,
            title = "Matcha Chia Parfait",
            description = "Layered matcha chia pudding with coconut yogurt, kiwi, berries, granola, and honey",
            category = "Snacks",
            difficulty = "Easy",
            timeMinutes = 10,
            servings = 2,
            rating = 4.5f,
            imageResId = R.drawable.matcha_chia_parfait,
            isFavorite = false,
            tags = listOf("make-ahead", "sweet"),
            ingredients = listOf(
                Ingredient("1/4 cup", "Chia seeds"),
                Ingredient("1 cup", "Milk or oat milk"),
                Ingredient("1 tsp", "Matcha powder"),
                Ingredient("1 tbsp", "Honey"),
                Ingredient("1/2 cup", "Coconut yogurt"),
                Ingredient("1", "Kiwi"),
                Ingredient("1/2 cup", "Mixed berries"),
                Ingredient("1/4 cup", "Granola")
            ),
            steps = listOf(
                "Whisk milk, matcha, honey, and chia seeds until evenly combined.",
                "Chill for at least 2 hours or overnight until thick.",
                "Slice kiwi and prepare berries.",
                "Layer matcha chia pudding with coconut yogurt in jars.",
                "Top with kiwi, berries, granola, and a small drizzle of honey.",
                "Serve chilled."
            )
        )
    )
}
