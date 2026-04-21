package com.example.resepkita.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resepkita.data.model.Recipe
import com.example.resepkita.ui.theme.BreakfastBadge
import com.example.resepkita.ui.theme.DarkCard
import com.example.resepkita.ui.theme.DessertBadge
import com.example.resepkita.ui.theme.DinnerBadge
import com.example.resepkita.ui.theme.FavoriteRed
import com.example.resepkita.ui.theme.FeaturedCardBg
import com.example.resepkita.ui.theme.LunchBadge
import com.example.resepkita.ui.theme.SnackBadge
import com.example.resepkita.ui.theme.TextSecondary

fun getCategoryColor(category: String): Color {
    return when (category) {
        "Breakfast" -> BreakfastBadge
        "Lunch" -> LunchBadge
        "Dinner" -> DinnerBadge
        "Dessert" -> DessertBadge
        "Snacks" -> SnackBadge
        else -> BreakfastBadge
    }
}

fun getCategoryBgColor(category: String): Color {
    return when (category) {
        "Breakfast" -> Color(0xFFE8F5E9)
        "Lunch" -> Color(0xFFFFF3E0)
        "Dinner" -> Color(0xFFFCE4EC)
        "Dessert" -> Color(0xFFF3E5F5)
        "Snacks" -> Color(0xFFE1F5FE)
        else -> FeaturedCardBg
    }
}

@Composable
fun RecipeCard(
    recipe: Recipe,
    onRecipeClick: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onRecipeClick(recipe.id) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = DarkCard)
    ) {
        Column {
            // Image area with category badge and favorite
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(
                        getCategoryBgColor(recipe.category),
                        RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
            ) {
                // Category badge
                Text(
                    text = recipe.category,
                    modifier = Modifier
                        .padding(8.dp)
                        .background(
                            getCategoryColor(recipe.category),
                            RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )

                // Favorite button
                IconButton(
                    onClick = { onFavoriteClick(recipe.id) },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = if (recipe.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (recipe.isFavorite) FavoriteRed else Color.Gray,
                        modifier = Modifier
                            .size(22.dp)
                            .then(
                                if (recipe.isFavorite) Modifier.background(
                                    Color.White.copy(alpha = 0.9f), CircleShape
                                ).padding(3.dp)
                                else Modifier.background(
                                    Color.White.copy(alpha = 0.9f), CircleShape
                                ).padding(3.dp)
                            )
                    )
                }

                // Emoji icon
                Text(
                    text = recipe.imageEmoji,
                    fontSize = 36.sp,
                    modifier = Modifier.align(Alignment.Center)
                )

                // Title label under emoji
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 4.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // Info section
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.AccessTime,
                            contentDescription = null,
                            tint = TextSecondary,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            "${recipe.timeMinutes}m",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondary
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFD700),
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            "${recipe.rating}",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondary
                        )
                    }
                }
            }
        }
    }
}
