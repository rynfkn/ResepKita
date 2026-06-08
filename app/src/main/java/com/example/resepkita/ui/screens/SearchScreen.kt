package com.example.resepkita.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resepkita.data.model.Recipe
import com.example.resepkita.ui.components.RecipeCard
import com.example.resepkita.ui.theme.Green50
import com.example.resepkita.ui.theme.extraColors

@Composable
fun SearchScreen(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    categories: List<String>,
    selectedCategory: String,
    onCategoryChange: (String) -> Unit,
    difficulties: List<String>,
    selectedDifficulty: String,
    onDifficultyChange: (String) -> Unit,
    selectedMaxTimeMinutes: Int?,
    onMaxTimeChange: (Int?) -> Unit,
    selectedMinRating: Float,
    onMinRatingChange: (Float) -> Unit,
    favoritesOnly: Boolean,
    onFavoritesOnlyChange: (Boolean) -> Unit,
    onClearFilters: () -> Unit,
    results: List<Recipe>,
    onRecipeClick: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val timeOptions = listOf(null to "Any", 15 to "<= 15m", 30 to "<= 30m", 45 to "<= 45m")
    val ratingOptions = listOf(0f to "Any", 4.5f to "4.5+", 4.8f to "4.8+")
    val hasActiveFilters = selectedCategory != "All" ||
        selectedDifficulty != "Any" ||
        selectedMaxTimeMinutes != null ||
        selectedMinRating > 0f ||
        favoritesOnly
    var showFilters by remember { mutableStateOf(false) }
    var draftCategory by remember(selectedCategory, showFilters) { mutableStateOf(selectedCategory) }
    var draftDifficulty by remember(selectedDifficulty, showFilters) { mutableStateOf(selectedDifficulty) }
    var draftMaxTimeMinutes by remember(selectedMaxTimeMinutes, showFilters) { mutableStateOf(selectedMaxTimeMinutes) }
    var draftMinRating by remember(selectedMinRating, showFilters) { mutableStateOf(selectedMinRating) }
    var draftFavoritesOnly by remember(favoritesOnly, showFilters) { mutableStateOf(favoritesOnly) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Search",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search recipes, ingredients...") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
            },
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.extraColors.input,
                focusedContainerColor = MaterialTheme.extraColors.input,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Green50,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = Green50
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { showFilters = !showFilters },
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (showFilters || hasActiveFilters) Green50 else MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = if (showFilters || hasActiveFilters) Color.White else MaterialTheme.colorScheme.onSurface
                )
            ) {
                Icon(Icons.Default.FilterList, contentDescription = null)
                Text(
                    if (hasActiveFilters) "Filters On" else "Filters",
                    modifier = Modifier.padding(start = 8.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            if (hasActiveFilters || showFilters) {
                TextButton(
                    onClick = {
                        draftCategory = "All"
                        draftDifficulty = "Any"
                        draftMaxTimeMinutes = null
                        draftMinRating = 0f
                        draftFavoritesOnly = false
                        onClearFilters()
                    }
                ) {
                    Text("Clear", color = Green50)
                }
            }
        }

        if (showFilters) {
            Spacer(modifier = Modifier.height(12.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(16.dp))
                    .padding(14.dp)
            ) {
                FilterOptionsRow(
                    title = "Category",
                    options = categories,
                    selectedOption = draftCategory,
                    onOptionSelected = { draftCategory = it }
                )
                Spacer(modifier = Modifier.height(10.dp))
                FilterOptionsRow(
                    title = "Difficulty",
                    options = difficulties,
                    selectedOption = draftDifficulty,
                    onOptionSelected = { draftDifficulty = it }
                )
                Spacer(modifier = Modifier.height(10.dp))
                FilterOptionsRow(
                    title = "Time",
                    options = timeOptions.map { it.second },
                    selectedOption = timeOptions.first { it.first == draftMaxTimeMinutes }.second,
                    onOptionSelected = { selectedLabel ->
                        draftMaxTimeMinutes = timeOptions.first { it.second == selectedLabel }.first
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                FilterOptionsRow(
                    title = "Rating",
                    options = ratingOptions.map { it.second },
                    selectedOption = ratingOptions.first { it.first == draftMinRating }.second,
                    onOptionSelected = { selectedLabel ->
                        draftMinRating = ratingOptions.first { it.second == selectedLabel }.first
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background, RoundedCornerShape(14.dp))
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Favorites only",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Switch(
                        checked = draftFavoritesOnly,
                        onCheckedChange = { draftFavoritesOnly = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = Green50,
                            uncheckedThumbColor = Color.Gray,
                            uncheckedTrackColor = MaterialTheme.colorScheme.outlineVariant
                        )
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        onCategoryChange(draftCategory)
                        onDifficultyChange(draftDifficulty)
                        onMaxTimeChange(draftMaxTimeMinutes)
                        onMinRatingChange(draftMinRating)
                        onFavoritesOnlyChange(draftFavoritesOnly)
                        showFilters = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Green50)
                ) {
                    Text("Apply Filters", fontWeight = FontWeight.Bold)
                }
            }
        } else if (hasActiveFilters) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "Filtered results",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (results.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("🔍", fontSize = 48.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    if (searchQuery.isBlank() && !hasActiveFilters) "Start searching for recipes" else "No recipes found",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(results) { recipe ->
                    RecipeCard(
                        recipe = recipe,
                        onRecipeClick = onRecipeClick,
                        onFavoriteClick = onFavoriteClick
                    )
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}

@Composable
private fun FilterOptionsRow(
    title: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            title,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium
        )
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            options.forEach { option ->
                val isSelected = option == selectedOption
                FilterChip(
                    selected = isSelected,
                    onClick = { onOptionSelected(option) },
                    label = { Text(option) },
                    shape = RoundedCornerShape(20.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        labelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        selectedContainerColor = Green50,
                        selectedLabelColor = Color.White
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        borderColor = Color.Transparent,
                        selectedBorderColor = Color.Transparent,
                        enabled = true,
                        selected = isSelected
                    )
                )
            }
        }
    }
}
