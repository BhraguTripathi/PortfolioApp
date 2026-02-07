package com.example.portfolio.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.portfolio.R
import com.example.portfolio.navigation.Screen
import com.example.portfolio.ui.components.*
import com.example.portfolio.ui.theme.*

/* ---------- DATA MODELS ---------- */

enum class ProjectType {
    AI, APP, BACKEND, CLOUD, MISCELLANEOUS, WEB
}

data class Project(
    val title: String,
    val imageRes: Int,
    val type: ProjectType,
    val techStack: List<String>,
    val githubUrl: String
)

/* ---------- SCREEN ---------- */

@Composable
fun ProjectScreen(navController: NavController) {

    val projects = listOf(
        Project(
            "Calculator App",
            R.drawable.calculator,
            ProjectType.APP,
            listOf("Kotlin", "Jetpack Compose"),
            "https://github.com/BhraguTripathi/CalculatorApp"
        ),
        Project(
            "Luxury Hotel Finder",
            R.drawable.luxaryhotalfinder,
            ProjectType.WEB,
            listOf("HTML", "CSS", "JavaScript"),
            "https://github.com/BhraguTripathi/Luxury-Hotel-Finder"
        ),
        Project(
            "Snake Game",
            R.drawable.snakegame,
            ProjectType.MISCELLANEOUS,
            listOf("JAVA"),
            "https://github.com/BhraguTripathi/SnakeGame"
        ),
        Project(
            "Real Time OS Security Event Logger",
            R.drawable.securityeventlogger,
            ProjectType.MISCELLANEOUS,
            listOf("Java", "JAVA Swing"),
            "https://github.com/BhraguTripathi/Real-Time-OS-Security-Logger"
        ),
        Project(
            "VougeVista-Fashion E-commerce",
            R.drawable.vougevista,
            ProjectType.WEB,
            listOf("HTML", "CSS", "JavaScript"),
            "https://github.com/BhraguTripathi/VougeVista"
        ),
        Project(
            "College Management System",
            R.drawable.collegemanagementsystem,
            ProjectType.WEB,
            listOf("HTML", "CSS", "PHP"),
            "https://github.com/BhraguTripathi/College-Management-System-main"
        )
    )

    var selectedFilter by remember { mutableStateOf("All") }

    val filteredProjects = when (selectedFilter) {
        "App" -> projects.filter { it.type == ProjectType.APP }
        "Web" -> projects.filter { it.type == ProjectType.WEB }
        "Cloud" -> projects.filter { it.type == ProjectType.CLOUD }
        "Backend" -> projects.filter { it.type == ProjectType.BACKEND }
        "AI" -> projects.filter { it.type == ProjectType.AI }
        else -> projects
    }

    GradientBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {

            TopBar(
                title = "Projects",
                showBackButton = false
            )

            ProjectFilterRow(
                selectedFilter = selectedFilter,
                onFilterSelected = { selectedFilter = it }
            )

            ContentCard(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(filteredProjects) { project ->
                        ProjectVerticalCard(project)
                    }
                }
            }

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

/* ---------- FILTER ROW (SCROLLABLE) ---------- */

@Composable
fun ProjectFilterRow(
    selectedFilter: String,
    onFilterSelected: (String) -> Unit
) {
    val filters = listOf("All", "AI" , "App", "Backend" , "Cloud" , "Web", "Miscellaneous" )

    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        filters.forEach { filter ->
            FilterChip(
                selected = selectedFilter == filter,
                onClick = { onFilterSelected(filter) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = PrimaryBlue,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                ),
                label = {
                    Text(text = filter, fontWeight = FontWeight.Medium)
                }
            )
        }
    }
}

/* ---------- PROJECT CARD ---------- */

@Composable
fun ProjectVerticalCard(project: Project) {

    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {

            Image(
                painter = painterResource(id = project.imageRes),
                contentDescription = project.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(16.dp)) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = project.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(
                        onClick = {
                            context.startActivity(
                                Intent(Intent.ACTION_VIEW, Uri.parse(project.githubUrl))
                            )
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.github),
                            contentDescription = "GitHub"
                            //tint = PrimaryBlue
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    project.techStack.forEach { tech ->
                        AssistChip(
                            onClick = {},
                            label = {
                                Text(text = tech, style = MaterialTheme.typography.labelSmall)
                            }
                        )
                    }
                }
            }
        }
    }
}

/* ---------- PREVIEW ---------- */

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProjectScreenPreview() {
    ProjectScreen(navController = rememberNavController())
}
