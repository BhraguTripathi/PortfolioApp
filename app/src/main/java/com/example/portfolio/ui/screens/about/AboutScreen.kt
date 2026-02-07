package com.example.portfolio.ui.screens.about

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.portfolio.R
import com.example.portfolio.navigation.Screen
import com.example.portfolio.ui.components.*
import com.example.portfolio.ui.theme.*

@Composable
fun MyStoryScreen(navController: NavController) {

    val isPreview = LocalInspectionMode.current
    val context = LocalContext.current

    // 🔗 Replace this with your actual resume link
    val resumeUrl = "https://drive.google.com/file/d/1TUhTU7FpwTJddZ9TAbDXDDMSBkf_YjF1/view?usp=sharing"

    GradientBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {

            /* ---------- TOP BAR ---------- */
            TopBar(
                title = "About",
                showBackButton = false
            )

            /* ---------- SCROLLABLE CONTENT ---------- */
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                /* ---------- ABOUT CARD (UNCHANGED) ---------- */
                ContentCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.profile2),
                            contentDescription = "Profile",
                            modifier = Modifier
                                .size(110.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .border(2.dp, PrimaryBlue, RoundedCornerShape(20.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.height(24.dp))

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

                /* ---------- RESUME CARD ---------- */
                ContentCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .clickable {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(resumeUrl)
                            )
                            context.startActivity(intent)
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = "Resume",
                            tint = PrimaryBlue
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Resume",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                            Text(
                                text = "View or download my CV",
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextSecondary
                            )
                        }

                        Icon(
                            imageVector = Icons.Default.OpenInNew,
                            contentDescription = "Open Resume",
                            tint = PrimaryBlue
                        )
                    }
                }

                /* ---------- EDUCATION CARD ---------- */
                ContentCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.School,
                                contentDescription = "Education",
                                tint = PrimaryBlue
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Education",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryBlue
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        EducationItem(
                            title = "Bachelor of Technology (B.Tech)",
                            subtitle = "Computer Science and Engineering",
                            institute = "Lovely Professional University, Punjab",
                            duration = "2023 – Present"
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        EducationItem(
                            title = "Senior Secondary (12th)",
                            subtitle = "Science Stream",
                            institute = "S. J. Education Center, Kanpur",
                            duration = "2022"
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        EducationItem(
                            title = "Secondary (10th)",
                            subtitle = "Science Stream",
                            institute = "S. J. Education Center, Kanpur",
                            duration = "2020"
                        )
                    }
                }
            }

            /* ---------- BOTTOM NAV ---------- */
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

/* ---------- EDUCATION ITEM ---------- */
@Composable
fun EducationItem(
    title: String,
    subtitle: String,
    institute: String,
    duration: String
) {
    Row {
        Box(
            modifier = Modifier
                .width(3.dp)
                .height(90.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(PrimaryBlue)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = institute,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary
            )

            Text(
                text = duration,
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )
        }
    }
}

/* ---------- PREVIEW ---------- */
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MyStoryScreenPreview() {
    PortfolioAppTheme {
        MyStoryScreen(navController = rememberNavController())
    }
}
