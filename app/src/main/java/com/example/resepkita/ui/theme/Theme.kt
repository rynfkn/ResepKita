package com.example.resepkita.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class ResepKitaExtraColors(
    val input: Color,
    val destructiveContainer: Color,
    val onDestructiveContainer: Color,
)

val LocalResepKitaExtraColors = staticCompositionLocalOf {
    ResepKitaExtraColors(
        input = DarkInput,
        destructiveContainer = Color(0xFF331111),
        onDestructiveContainer = FavoriteRed,
    )
}

val MaterialTheme.extraColors: ResepKitaExtraColors
    @Composable
    get() = LocalResepKitaExtraColors.current

private val DarkExtraColors = ResepKitaExtraColors(
    input = DarkInput,
    destructiveContainer = Color(0xFF331111),
    onDestructiveContainer = FavoriteRed,
)

private val LightExtraColors = ResepKitaExtraColors(
    input = LightInput,
    destructiveContainer = Color(0xFFFFECEC),
    onDestructiveContainer = Color(0xFFC62828),
)

private val DarkResepKitaColorScheme = darkColorScheme(
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

private val LightResepKitaColorScheme = lightColorScheme(
    primary = Green40,
    onPrimary = Color.White,
    primaryContainer = GreenLight,
    onPrimaryContainer = Green40,
    secondary = Green50,
    onSecondary = Color.White,
    background = LightBackground,
    onBackground = LightTextPrimary,
    surface = LightSurface,
    onSurface = LightTextPrimary,
    surfaceVariant = LightCard,
    onSurfaceVariant = LightTextSecondary,
    outline = LightBorder,
    outlineVariant = LightBorder,
)

@Composable
fun ResepKitaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkResepKitaColorScheme else LightResepKitaColorScheme
    val extraColors = if (darkTheme) DarkExtraColors else LightExtraColors

    CompositionLocalProvider(LocalResepKitaExtraColors provides extraColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
