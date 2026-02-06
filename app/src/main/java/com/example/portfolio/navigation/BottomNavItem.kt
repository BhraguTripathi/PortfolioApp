package com.example.portfolio.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    object Home : BottomNavItem(
        route = Screen.Home.route,
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    )

    object About : BottomNavItem(
        route = Screen.MyStory.route,
        title = "About",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    )

    object Projects : BottomNavItem(
        route = Screen.Project.route,
        title = "Projects",
        selectedIcon = Icons.Filled.Build,
        unselectedIcon = Icons.Outlined.Build
    )

    object Contact : BottomNavItem(
        route = Screen.Contact.route,
        title = "Contact",
        selectedIcon = Icons.Filled.Phone,
        unselectedIcon = Icons.Outlined.Phone
    )

    object Skills : BottomNavItem(
        route = Screen.Skills.route,
        title = "Skills",
        selectedIcon = Icons.Filled.BarChart,
        unselectedIcon = Icons.Outlined.BarChart
    )

    object Favorites : BottomNavItem(
        route = Screen.Skills.route,
        title = "Favorites",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder
    )
}