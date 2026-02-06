package com.example.portfolio.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.portfolio.ui.screens.ProjectScreen
import com.example.portfolio.ui.screens.about.MyStoryScreen
import com.example.portfolio.ui.screens.contact.ContactScreen
import com.example.portfolio.ui.screens.home.HomeScreen
import com.example.portfolio.ui.screens.skills.SkillsScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route,
            enterTransition = {
                fadeIn(animationSpec = tween(300)) + slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                fadeOut(animationSpec = tween(300)) + slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(300)
                )
            }
        ) {
            HomeScreen(navController = navController)
        }

        composable(
            route = Screen.MyStory.route,
            enterTransition = {
                fadeIn(animationSpec = tween(300)) + slideInHorizontally(
                    initialOffsetX = { 300 },
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                fadeOut(animationSpec = tween(300)) + slideOutHorizontally(
                    targetOffsetX = { 300 },
                    animationSpec = tween(300)
                )
            }
        ) {
            MyStoryScreen(navController = navController)
        }

        composable(
            route = Screen.Skills.route,
            enterTransition = {
                fadeIn(animationSpec = tween(300)) + slideInHorizontally(
                    initialOffsetX = { 300 },
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                fadeOut(animationSpec = tween(300)) + slideOutHorizontally(
                    targetOffsetX = { 300 },
                    animationSpec = tween(300)
                )
            }
        ) {
            SkillsScreen(navController = navController)
        }

        composable(
            route = Screen.Contact.route,
            enterTransition = {
                fadeIn(animationSpec = tween(300)) + slideInHorizontally(
                    initialOffsetX = { 300 },
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                fadeOut(animationSpec = tween(300)) + slideOutHorizontally(
                    targetOffsetX = { 300 },
                    animationSpec = tween(300)
                )
            }
        ) {
            ContactScreen(navController = navController)
        }

        composable(
            route = Screen.Project.route,
            enterTransition = {
                fadeIn(animationSpec = tween(300)) + slideInHorizontally(
                    initialOffsetX = { 300 },
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                fadeOut(animationSpec = tween(300)) + slideOutHorizontally(
                    targetOffsetX = { 300 },
                    animationSpec = tween(300)
                )
            }
        ) {
            ProjectScreen(navController = navController)
        }
    }
}