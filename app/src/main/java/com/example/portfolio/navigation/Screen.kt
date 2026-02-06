package com.example.portfolio.navigation

/**
 * Sealed class representing all navigation destinations in the app.
 */
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object MyStory : Screen("my_story")
    object Skills : Screen("skills")
    object Contact : Screen("contact")
    object Project : Screen("project")
}
