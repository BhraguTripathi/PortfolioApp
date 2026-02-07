package com.example.portfolio.ui.screens.contact

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.portfolio.R
import com.example.portfolio.navigation.Screen
import com.example.portfolio.ui.components.*
import com.example.portfolio.ui.theme.*

@Composable
fun ContactScreen(navController: NavController) {

    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") } // ✅ Added back (UX only)
    var message by remember { mutableStateOf("") }

    GradientBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {

            /* ---------------- Header ---------------- */
            TopBar(
                title = "Contact",
                showBackButton = false,
                onBackClick = {}
            )

            /* ---------------- Content ---------------- */
            ContentCard(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Get in touch",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )

                    /* Input Fields */
                    RoundedField(
                        value = name,
                        label = "Your Name",
                        onValueChange = { name = it }
                    )

                    RoundedField(
                        value = email,
                        label = "Your Email",
                        onValueChange = { email = it }
                    )

                    RoundedField(
                        value = message,
                        label = "Message",
                        onValueChange = { message = it },
                        height = 120.dp
                    )

                    /* Send Icon Button */
                    Button(
                        onClick = {
                            val subject = "Portfolio Contact"
                            val body = """
$message

— $name
        """.trimIndent()

                            val uri = Uri.Builder()
                                .scheme("mailto")
                                .opaquePart("tripathibhragu@gmail.com")
                                .appendQueryParameter("subject", subject)
                                .appendQueryParameter("body", body)
                                .build()

                            context.startActivity(
                                Intent(Intent.ACTION_SENDTO, uri)
                            )

                            // Clear form
                            message = ""
                        },
                        shape = RoundedCornerShape(16.dp),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryBlue
                        )
                    ) {
                        Text(
                            text = "Send",
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Filled.Send,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }


                    Spacer(modifier = Modifier.height(5.dp))

                    /* ---------------- Social Icons ---------------- */
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        SocialIconVector(Icons.Filled.Phone) {
                            context.startActivity(
                                Intent(Intent.ACTION_DIAL, Uri.parse("tel:+919569164010"))
                            )
                        }

                        SocialIconDrawable(R.drawable.instagram) {
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://www.instagram.com/bhragutripathi_03/")
                                )
                            )
                        }

                        SocialIconDrawable(R.drawable.linkedin) {
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://linkedin.com/in/bhragu-tripathi")
                                )
                            )
                        }

                        SocialIconDrawable(R.drawable.github) {
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://github.com/BhraguTripathi")
                                )
                            )
                        }

                        SocialIconDrawable(R.drawable.x) {
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://x.com/bhragu_003")
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    /* ---------------- Location ---------------- */
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = LightBlueBackground
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                context.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("geo:0,0?q=Kanpur, Uttar Pradesh")
                                    )
                                )
                            }
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Place,
                                contentDescription = null,
                                tint = PrimaryBlue
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Kanpur, Uttar Pradesh, India",
                                color = TextPrimary
                            )
                        }
                    }
                }
            }

            /* ---------------- Bottom Nav ---------------- */
            BottomNavigationBar(
                currentRoute = Screen.Contact.route,
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

/* ---------- Rounded Field ---------- */
@Composable
private fun RoundedField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    height: Dp = Dp.Unspecified
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .let { if (height != Dp.Unspecified) it.height(height) else it },
        shape = RoundedCornerShape(16.dp)
    )
}

/* ---------- Social Icon (Vector) ---------- */
@Composable
private fun SocialIconVector(
    icon: ImageVector,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(52.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(LightBlueBackground)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = PrimaryBlue
        )
    }
}

/* ---------- Social Icon (Drawable / Brand) ---------- */
@Composable
private fun SocialIconDrawable(
    iconRes: Int,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(52.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(LightBlueBackground)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )
    }
}

/* ---------------- Preview ---------------- */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContactScreenPreview() {
    PortfolioAppTheme {
        ContactScreen(navController = rememberNavController())
    }
}
