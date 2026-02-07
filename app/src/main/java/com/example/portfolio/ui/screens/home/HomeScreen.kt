package com.example.portfolio.ui.screens.home

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.portfolio.R
import com.example.portfolio.navigation.Screen
import com.example.portfolio.ui.components.BottomNavigationBar
import com.example.portfolio.ui.components.ContentCard
import com.example.portfolio.ui.components.GradientBackground
import com.example.portfolio.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(navController: NavController) {

    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(120)
        isVisible = true
    }

    val imageScale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.85f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "imageScale"
    )

    val cardOffset by animateDpAsState(
        targetValue = if (isVisible) 0.dp else 90.dp,
        animationSpec = tween(500, easing = EaseOutCubic),
        label = "cardOffset"
    )

    /* ---------------- Wave Animation ---------------- */
    val infiniteTransition =
        rememberInfiniteTransition(label = "waveTransition")

    val waveRotation by infiniteTransition.animateFloat(
        initialValue = -12f,
        targetValue = 12f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 700,
                easing = EaseInOut
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "waveRotation"
    )

    GradientBackground {
        Column(modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
        ) {

            /* ---------------- Top Bar ---------------- */
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Home",
                    style = MaterialTheme.typography.headlineLarge,
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold
                )

                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .border(2.dp, PrimaryBlue, CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            /* ---------------- Main Content ---------------- */
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {

                ContentCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.85f)
                        .graphicsLayer {
                            scaleX = imageScale
                            scaleY = imageScale
                        }
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {

                        Image(
                            painter = painterResource(id = R.drawable.profile3),
                            contentDescription = "Profile",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(24.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.5f)
                                .align(Alignment.BottomCenter)
                                .background(
                                    Brush.verticalGradient(
                                        listOf(
                                            Color.Transparent,
                                            Color.White.copy(alpha = 0.9f)
                                        )
                                    )
                                )
                        )
                    }
                }

                /* ---------------- Info Card ---------------- */
                Card(
                    modifier = Modifier.fillMaxWidth()
                        .align(Alignment.BottomStart)
                        .padding(start = 8.dp, bottom = 16.dp)
                        .offset(y = cardOffset )
                        .shadow(8.dp, RoundedCornerShape(20.dp)),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Hello",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "👋",
                                fontSize = 30.sp,
                                modifier = Modifier.graphicsLayer {
                                    rotationZ = waveRotation
                                    transformOrigin =
                                        TransformOrigin(0.7f, 1f)
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    SpanStyle(
                                        color = TextPrimary,
                                        fontWeight = FontWeight.Bold
                                    )
                                ) {
                                    append("I'm ")
                                }
                                withStyle(
                                    SpanStyle(
                                        color = PrimaryBlue,
                                        fontWeight = FontWeight.Bold
                                    )
                                ) {
                                    append("Bhragu Tripathi")
                                }
                            },
                            style = MaterialTheme.typography.headlineMedium
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Native Android Developer",
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.bodyLarge,
                            color = TextSecondary
                        )
                    }
                }
            }

            /* ---------------- Bottom Navigation ---------------- */
            BottomNavigationBar(
                currentRoute = Screen.Home.route,
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

/* ---------------- Easings ---------------- */
private val EaseOutCubic =
    CubicBezierEasing(0.33f, 1f, 0.68f, 1f)

private val EaseInOut =
    CubicBezierEasing(0.42f, 0f, 0.58f, 1f)

/* ---------------- Preview ---------------- */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    PortfolioAppTheme {
        HomeScreen(navController = rememberNavController())
    }
}
