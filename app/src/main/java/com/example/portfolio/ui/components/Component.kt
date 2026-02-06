package com.example.portfolio.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.navigation.BottomNavItem
import com.example.portfolio.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun GradientBackground(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        LightBlueBackground,
                        LightBlueDark.copy(alpha = 0.3f)
                    )
                )
            )
    ) {
        content()
    }
}

@Composable
fun TopBar(
    title: String,
    showBackButton: Boolean = false,
    onBackClick: () -> Unit = {},
    trailingContent: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            if (showBackButton) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.offset(x = (-12).dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = TextPrimary
                    )
                }
            }
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                color = TextPrimary,
                fontWeight = FontWeight.Bold
            )
        }
        trailingContent?.invoke()
    }
}

@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onItemClick: (BottomNavItem) -> Unit
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.About,
        BottomNavItem.Skills,
        BottomNavItem.Projects,
        BottomNavItem.Contact
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                BottomNavItemView(
                    item = item,
                    isSelected = currentRoute == item.route,
                    onClick = { onItemClick(item) }
                )
            }
        }
    }
}

@Composable
fun BottomNavItemView(
    item: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val animatedSize by animateDpAsState(
        targetValue = if (isSelected) 48.dp else 40.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "size"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(animatedSize)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    if (isSelected) PrimaryBlue.copy(alpha = 0.2f)
                    else Color.Transparent
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                contentDescription = item.title,
                tint = if (isSelected) PrimaryBlue else IconGray,
                modifier = Modifier.size(24.dp)
            )
        }
        if (isSelected) {
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = item.title,
                style = MaterialTheme.typography.labelMedium,
                color = PrimaryBlue,
                fontSize = 10.sp
            )
        }
    }
}

@Composable
fun ContentCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(24.dp),
                ambientColor = Color.Black.copy(alpha = 0.1f),
                spotColor = Color.Black.copy(alpha = 0.1f)
            ),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        content()
    }
}

@Composable
fun AnimatedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = DarkBlue,
    textColor: Color = Color.White
) {
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "scale"
    )

    Button(
        onClick = {
            isPressed = true
            onClick()
        },
        modifier = modifier
            .height(56.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 6.dp,
            pressedElevation = 2.dp
        )
    ) {
        Text(
            text = text,
            color = textColor,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )
    }

    LaunchedEffect(isPressed) {
        if (isPressed) {
            delay(100)
            isPressed = false
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    minHeight: Int = 56
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                color = TextSecondary
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = minHeight.dp),
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryBlue,
            unfocusedBorderColor = LightBlueDark.copy(alpha = 0.5f),
            focusedContainerColor = LightBlueBackground.copy(alpha = 0.3f),
            unfocusedContainerColor = LightBlueBackground.copy(alpha = 0.3f)
        ),
        singleLine = singleLine
    )
}

@Composable
fun SocialIcon(
    icon: ImageVector,
    onClick: () -> Unit,
    backgroundColor: Color = IconGray.copy(alpha = 0.3f)
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TextSecondary,
            modifier = Modifier.size(24.dp)
        )
    }
}