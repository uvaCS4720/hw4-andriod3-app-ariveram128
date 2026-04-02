package edu.nd.pmcburne.hello.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = UvaOrange,
    onPrimary = Color.White,
    primaryContainer = UvaNavyLight,
    onPrimaryContainer = Color.White,
    secondary = UvaNavyLight,
    onSecondary = Color.White,
    background = SurfaceDark,
    onBackground = OnSurfaceDark,
    surface = CardDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = UvaNavyDark,
    onSurfaceVariant = OnSurfaceDark
)

private val LightColorScheme = lightColorScheme(
    primary = UvaNavy,
    onPrimary = Color.White,
    primaryContainer = UvaOrange,
    onPrimaryContainer = Color.White,
    secondary = UvaOrange,
    onSecondary = Color.White,
    background = SurfaceLight,
    onBackground = OnSurfaceLight,
    surface = CardLight,
    onSurface = OnSurfaceLight,
    surfaceVariant = Color(0xFFEDE8E0),
    onSurfaceVariant = UvaNavy
)

@Composable
fun CampusMapsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
