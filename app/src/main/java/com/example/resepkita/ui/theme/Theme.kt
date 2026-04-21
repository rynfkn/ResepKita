package com.example.resepkita.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ResepKitaColorScheme = darkColorScheme(
    primary = Green50,
    onPrimary = Color.White,
    primaryContainer = Green40,
    onPrimaryContainer = Color.White,
    secondary = Green60,
    onSecondary = Color.White,
    background = DarkBackground,
    onBackground = TextPrimary,
    surface = DarkSurface,
    onSurface = TextPrimary,
    surfaceVariant = DarkCard,
    onSurfaceVariant = TextSecondary,
    outline = DarkBorder,
    outlineVariant = DarkBorder,
)

@Composable
fun ResepKitaTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = ResepKitaColorScheme,
        typography = Typography,
        content = content
    )
}