package com.example.portfolio.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.portfolio.R
import com.example.portfolio.navigation.Screen
import com.example.portfolio.ui.components.*
import com.example.portfolio.ui.theme.*
import kotlinx.coroutines.delay

data class Project(
    val title: String,
    val imageRes: Int
)

@Composable
fun ProjectScreen(navController: NavController) {
    val projects = listOf(
        Project("E-commerce\nSite", R.drawable.profile),
        Project("Fitness App", R.drawable.profile),
        Project("Design\nSystem", R.drawable.profile),
        Project("Portfolio\nWebsite", R.drawable.profile)
    )

    GradientBackground {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top Bar
            TopBar(
                title = "Projects",
                showBackButton = true,
                onBackClick = { navController.popBackStack() }
            )

            // Projects Grid
            ContentCard(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    itemsIndexed(projects) { index, project ->
                        AnimatedProjectCard(
                            project = project,
                            index = index,
                            onClick = { /* Navigate to project detail */ }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Bottom Navigation
            BottomNavigationBar(
                currentRoute = Screen.Project.route,
                onItemClick = { item ->
                    navController.navigate(item.route) {
                        popUpTo(Screen.Home.route) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun AnimatedProjectCard(
    project: Project,
    index: Int,
    onClick: () -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(index * 150L)
        isVisible = true
    }

    val cardAlpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(400),
        label = "alpha"
    )

    val cardScale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.8f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scale"
    )

    // Hover/Press animation
    var isPressed by remember { mutableStateOf(false) }
    val pressScale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "pressScale"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.85f)
            .graphicsLayer {
                alpha = cardAlpha
                scaleX = cardScale * pressScale
                scaleY = cardScale * pressScale
            },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardBackground
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = {
            isPressed = true
            onClick()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Project Image
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Image(
                    painter = painterResource(id = project.imageRes),
                    contentDescription = project.title,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Project Title
            Text(
                text = project.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }

    LaunchedEffect(isPressed) {
        if (isPressed) {
            delay(100)
            isPressed = false
        }
    }
}

private val EaseOutCubic = CubicBezierEasing(0.33f, 1f, 0.68f, 1f)