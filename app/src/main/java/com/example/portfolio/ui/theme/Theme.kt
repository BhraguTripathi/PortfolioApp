package com.example.portfolio.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    secondary = DarkBlue,
    background = LightBlueBackground,
    surface = CardBackground,
    onPrimary = CardBackground,
    onSecondary = CardBackground,
    onBackground = TextPrimary,
    onSurface = TextPrimary
)

@Composable
fun PortfolioAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}