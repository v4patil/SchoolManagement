package com.vibhorpatil.schoolmanagement.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(

    primary = primaryBlue,
    secondary = secondaryTeal,

    background = textPrimaryNavy,
    surface = Color(0xFF1E293B),

    onPrimary = surfaceWhite,
    onSecondary = surfaceWhite,

    onBackground = surfaceWhite,
    onSurface = surfaceWhite,

    error = errorRed,
    onError = surfaceWhite
)

private val LightColorPalette = lightColorScheme(

    primary = primaryBlue,
    secondary = secondaryTeal,

    background = backgroundGray,
    surface = surfaceWhite,

    onPrimary = surfaceWhite,
    onSecondary = surfaceWhite,

    onBackground = textPrimaryNavy,
    onSurface = textPrimaryNavy,

    error = errorRed,
    onError = surfaceWhite
)

@Composable
fun ComposeSampleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes(),
        content = content
    )
}