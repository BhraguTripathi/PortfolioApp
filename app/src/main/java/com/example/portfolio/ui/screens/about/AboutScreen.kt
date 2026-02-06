package com.example.portfolio.ui.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.portfolio.R
import com.example.portfolio.navigation.Screen
import com.example.portfolio.ui.components.BottomNavigationBar
import com.example.portfolio.ui.components.ContentCard
import com.example.portfolio.ui.components.GradientBackground
import com.example.portfolio.ui.components.TopBar
import com.example.portfolio.ui.theme.*

@Composable
fun MyStoryScreen(navController: NavController) {

    val isPreview = LocalInspectionMode.current

    GradientBackground {
        Column(modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
        ) {

            /* ---------------- Header (Safe from Status Bar) ---------------- */
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()   // ✅ KEY FIX
            ) {
                TopBar(
                    title = "About",
                    showBackButton = false,
                    onBackClick = {}
                )
            }

            /* ---------------- Card Area ---------------- */
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {

                ContentCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {

                    /* Scroll INSIDE the card */
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        /* Profile Image */
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Profile",
                            modifier = Modifier
                                .size(110.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .border(
                                    2.dp,
                                    PrimaryBlue,
                                    RoundedCornerShape(20.dp)
                                ),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        /* Letter-style content */
                        Text(
                            text = "I am Bhragu Tripathi, a Computer Science and Engineering student with a strong interest in Android development and modern mobile UI design. I enjoy creating clean, intuitive, and user-focused applications that solve real-world problems.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = TextPrimary,
                            textAlign = TextAlign.Start,
                            lineHeight = 24.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "My journey into development began with curiosity about how mobile applications work behind the scenes. What started as simple experimentation gradually turned into a passion for Android development, UI/UX design, and writing clean, maintainable code.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = TextPrimary,
                            textAlign = TextAlign.Start,
                            lineHeight = 24.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Currently, I am focused on improving my Android development skills using Kotlin and Jetpack Compose. I actively work on personal projects, learn modern app architecture, and strengthen my problem-solving abilities through consistent practice.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = TextPrimary,
                            textAlign = TextAlign.Start,
                            lineHeight = 24.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Looking ahead, my goal is to grow as a skilled software developer, contribute to meaningful projects, and build applications that create real value for users. I believe in continuous learning and enjoy exploring new technologies that help me improve both technically and creatively.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = TextPrimary,
                            textAlign = TextAlign.Start,
                            lineHeight = 24.sp
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        /* Signature */
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                text = "— Bhragu Tripathi",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Medium,
                                color = PrimaryBlue
                            )
                        }
                    }
                }
            }

            /* ---------------- Bottom Navigation ---------------- */
            if (!isPreview) {
                BottomNavigationBar(
                    currentRoute = Screen.MyStory.route,
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
}

/* ---------------- Preview ---------------- */
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MyStoryScreenPreview() {
    PortfolioAppTheme {
        MyStoryScreen(navController = rememberNavController())
    }
}
