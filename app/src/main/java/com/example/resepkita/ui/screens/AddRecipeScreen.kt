package com.example.resepkita.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resepkita.data.model.Ingredient
import com.example.resepkita.data.model.Recipe
import com.example.resepkita.ui.theme.DarkCard
import com.example.resepkita.ui.theme.DarkInput
import com.example.resepkita.ui.theme.Green50
import com.example.resepkita.ui.theme.GreenLight
import com.example.resepkita.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipeScreen(
    onBack: () -> Unit,
    onSave: (Recipe) -> Unit,
    modifier: Modifier = Modifier
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Breakfast") }
    var difficulty by remember { mutableStateOf("Easy") }
    var timeMinutes by remember { mutableStateOf("") }
    var servings by remember { mutableStateOf("") }
    val ingredients = remember { mutableStateListOf(Ingredient("", "")) }
    val steps = remember { mutableStateListOf("") }
    var categoryExpanded by remember { mutableStateOf(false) }
    var difficultyExpanded by remember { mutableStateOf(false) }

    val fieldColors = OutlinedTextFieldDefaults.colors(
        unfocusedContainerColor = DarkInput,
        focusedContainerColor = DarkInput,
        unfocusedBorderColor = Color.Transparent,
        focusedBorderColor = Green50,
        unfocusedPlaceholderColor = TextSecondary,
        focusedPlaceholderColor = TextSecondary,
        unfocusedTextColor = Color.White,
        focusedTextColor = Color.White,
        cursorColor = Green50
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Top bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            Text(
                "New Recipe",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            // Cover photo placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .background(GreenLight.copy(alpha = 0.15f), RoundedCornerShape(16.dp))
                    .border(2.dp, Green50.copy(alpha = 0.3f), RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("📷", fontSize = 32.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Add cover photo", color = Green50, style = MaterialTheme.typography.bodySmall)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Form fields
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkCard, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Recipe title *") },
                    shape = RoundedCornerShape(12.dp),
                    colors = fieldColors,
                    singleLine = true
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier.fillMaxWidth().height(80.dp),
                    placeholder = { Text("Short description..") },
                    shape = RoundedCornerShape(12.dp),
                    colors = fieldColors,
                    maxLines = 3
                )

                // Category and difficulty dropdowns
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = categoryExpanded,
                        onExpandedChange = { categoryExpanded = it },
                        modifier = Modifier.weight(1f)
                    ) {
                        OutlinedTextField(
                            value = category,
                            onValueChange = {},
                            readOnly = true,
                            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable),
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                            shape = RoundedCornerShape(12.dp),
                            colors = fieldColors,
                            singleLine = true
                        )
                        ExposedDropdownMenu(
                            expanded = categoryExpanded,
                            onDismissRequest = { categoryExpanded = false }
                        ) {
                            listOf("Breakfast", "Lunch", "Dinner", "Dessert", "Snacks").forEach {
                                DropdownMenuItem(
                                    text = { Text(it) },
                                    onClick = { category = it; categoryExpanded = false }
                                )
                            }
                        }
                    }

                    ExposedDropdownMenuBox(
                        expanded = difficultyExpanded,
                        onExpandedChange = { difficultyExpanded = it },
                        modifier = Modifier.weight(1f)
                    ) {
                        OutlinedTextField(
                            value = difficulty,
                            onValueChange = {},
                            readOnly = true,
                            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable),
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = difficultyExpanded) },
                            shape = RoundedCornerShape(12.dp),
                            colors = fieldColors,
                            singleLine = true
                        )
                        ExposedDropdownMenu(
                            expanded = difficultyExpanded,
                            onDismissRequest = { difficultyExpanded = false }
                        ) {
                            listOf("Easy", "Medium", "Hard").forEach {
                                DropdownMenuItem(
                                    text = { Text(it) },
                                    onClick = { difficulty = it; difficultyExpanded = false }
                                )
                            }
                        }
                    }
                }

                // Time and servings
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = timeMinutes,
                        onValueChange = { timeMinutes = it.filter { c -> c.isDigit() } },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Time (min)") },
                        shape = RoundedCornerShape(12.dp),
                        colors = fieldColors,
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = servings,
                        onValueChange = { servings = it.filter { c -> c.isDigit() } },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Servings") },
                        shape = RoundedCornerShape(12.dp),
                        colors = fieldColors,
                        singleLine = true
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Ingredients section
            Text(
                "Ingredients",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkCard, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ingredients.forEachIndexed { index, ingredient ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = ingredient.amount,
                            onValueChange = { ingredients[index] = ingredient.copy(amount = it) },
                            modifier = Modifier.width(90.dp),
                            placeholder = { Text("Amount") },
                            shape = RoundedCornerShape(10.dp),
                            colors = fieldColors,
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = ingredient.name,
                            onValueChange = { ingredients[index] = ingredient.copy(name = it) },
                            modifier = Modifier.weight(1f),
                            placeholder = { Text("Ingredient") },
                            shape = RoundedCornerShape(10.dp),
                            colors = fieldColors,
                            singleLine = true
                        )
                        if (ingredients.size > 1) {
                            IconButton(
                                onClick = { ingredients.removeAt(index) },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(Icons.Default.Close, contentDescription = "Remove", tint = TextSecondary, modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                }
                TextButton(
                    onClick = { ingredients.add(Ingredient("", "")) },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("+ Add ingredient", color = Green50)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Steps section
            Text(
                "Steps",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                steps.forEachIndexed { index, step ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(top = 12.dp)
                                .size(28.dp)
                                .background(Green50, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("${index + 1}", color = Color.White, style = MaterialTheme.typography.labelMedium)
                        }
                        OutlinedTextField(
                            value = step,
                            onValueChange = { steps[index] = it },
                            modifier = Modifier.weight(1f).height(72.dp),
                            placeholder = { Text("Step ${index + 1}...") },
                            shape = RoundedCornerShape(12.dp),
                            colors = fieldColors,
                            maxLines = 3
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(
                onClick = { steps.add("") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkCard.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
            ) {
                Icon(Icons.Default.Add, contentDescription = null, tint = Green50, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("+ Add step", color = Green50)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Save button fixed at bottom
        Button(
            onClick = {
                if (title.isNotBlank()) {
                    val recipe = Recipe(
                        title = title,
                        description = description,
                        category = category,
                        difficulty = difficulty,
                        timeMinutes = timeMinutes.toIntOrNull() ?: 0,
                        servings = servings.toIntOrNull() ?: 1,
                        rating = 0f,
                        imageEmoji = when (category) {
                            "Breakfast" -> "🍳"
                            "Lunch" -> "🥗"
                            "Dinner" -> "🍽️"
                            "Dessert" -> "🍰"
                            "Snacks" -> "🍿"
                            else -> "🍽️"
                        },
                        ingredients = ingredients.filter { it.name.isNotBlank() },
                        steps = steps.filter { it.isNotBlank() }
                    )
                    onSave(recipe)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(52.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green50)
        ) {
            Text("Save Recipe", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        }
    }
}
