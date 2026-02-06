package com.example.portfolio.ui.screens.skills

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.portfolio.navigation.Screen
import com.example.portfolio.ui.components.*
import com.example.portfolio.ui.theme.*

/* ---------------- Models ---------------- */

data class SkillItem(
    val name: String,
    val icon: ImageVector
)

data class SkillCategory(
    val title: String,
    val skills: List<SkillItem>
)

/* ---------------- Screen ---------------- */

@Composable
fun SkillsScreen(navController: NavController) {

    val skillCategories = listOf(

        SkillCategory(
            title = "Programming Languages",
            skills = listOf(
                SkillItem("Java", Icons.Default.Code),
                SkillItem("Kotlin", Icons.Default.Code),
                SkillItem("C++", Icons.Default.Code),
                SkillItem("Python", Icons.Default.Code),
                SkillItem("SQL", Icons.Default.Storage),
                SkillItem("JavaScript", Icons.Default.Code)
            )
        ),

        SkillCategory(
            title = "Frameworks & UI",
            skills = listOf(
                SkillItem("HTML & CSS", Icons.Default.Web),
                SkillItem("Java Swing", Icons.Default.Window),
                SkillItem("Jetpack Compose", Icons.Default.PhoneAndroid)
            )
        ),

        SkillCategory(
            title = "Tools & Platforms",
            skills = listOf(
                SkillItem("Android Studio", Icons.Default.Android),
                SkillItem("IntelliJ IDEA", Icons.Default.Terminal),
                SkillItem("VS Code", Icons.Default.Code),
                SkillItem("Git", Icons.Default.Source),
                SkillItem("GitHub", Icons.Default.Code),
                SkillItem("AWS", Icons.Default.Cloud),
                SkillItem("MySQL", Icons.Default.Storage),
                SkillItem("Postman", Icons.Default.Api)
            )
        ),

        SkillCategory(
            title = "Soft Skills",
            skills = listOf(
                SkillItem("Problem Solving", Icons.Default.Psychology),
                SkillItem("Teamwork", Icons.Default.Groups),
                SkillItem("Leadership", Icons.Default.Star),
                SkillItem("Time Management", Icons.Default.Schedule)
            )
        )
    )

    GradientBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {

            /* ---------------- Top Bar ---------------- */
            TopBar(
                title = "Skills",
                showBackButton = false,
                onBackClick = { navController.popBackStack() }
            )

            /* ---------------- Content ---------------- */
            ContentCard(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        top = 20.dp,
                        bottom = 16.dp + 56.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(28.dp)
                ) {
                    items(skillCategories) { category ->
                        SkillCategorySection(category)
                    }
                }
            }

            /* ---------------- Bottom Nav ---------------- */
            BottomNavigationBar(
                currentRoute = Screen.Skills.route,
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

/* ---------------- Category Section ---------------- */

@Composable
private fun SkillCategorySection(category: SkillCategory) {

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = category.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            category.skills.forEach { skill ->
                SkillChip(skill)
            }
        }
    }
}

/* ---------------- Skill Chip ---------------- */

@Composable
private fun SkillChip(skill: SkillItem) {

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(LightBlueBackground)
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Icon(
            imageVector = skill.icon,
            contentDescription = skill.name,
            tint = PrimaryBlue,
            modifier = Modifier.size(18.dp)
        )

        Text(
            text = skill.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = TextPrimary
        )
    }
}

/* ---------------- Preview ---------------- */

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SkillsScreenPreview() {
    PortfolioAppTheme {
        SkillsScreen(navController = rememberNavController())
    }
}
