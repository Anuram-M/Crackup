package com.kumar.crackup.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.templates.ColoredButton
import com.kumar.crackup.templates.HeightSpacer
import com.kumar.crackup.templates.SmallHeightSpacer
import com.kumar.crackup.ui.theme.amber
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.ui.theme.bodyFontSize
import com.kumar.crackup.ui.theme.bodyFontSmallSize
import com.kumar.crackup.ui.theme.bodyFontTinySize
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.textBlack

@Composable
fun HeaderSection(
    userName: String = "Arun",
    userLevel: Int = 12,
    streakDays: Int = 5,
    coins: Int = 450,
    dailyProgress: Float = 0.65f,
    onStartQuizClick: () -> Unit = {}
) {
    // Top Half Container with Rounded Bottom Corners & Gradient
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f) // Occupies exactly 50% of screen height
            .padding(bottom = 10.dp),
        shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp),
        color = lightBlue
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
//                .background(
//                    brush = Brush.verticalGradient(
//                        colors = listOf(
//                            Color(0xFF4A00E0), // Deep Purple
//                            Color(0xFF8E2DE2)  // Electric Violet
//                        )
//                    )
//                )
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // -------------------------------------------------------------
                // 1. TOP BAR: User Avatar, Name, Stats Badges & Notification
                // -------------------------------------------------------------
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Profile & Greeting
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            modifier = Modifier.size(48.dp),
                            shape = CircleShape,
                            color = Color.White.copy(alpha = 0.2f)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                BodyText(
                                    text = userName.take(1).uppercase(),
                                    textColor = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    textSize = bodyFontLargeSize
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            BodyText(
                                text = "Hello, $userName 👋",
                                textColor = Color.White,
                                fontWeight = FontWeight.Bold,
                                textSize = 18.sp
                            )
//                            Text(
//                                text = "Level $userLevel Explorer",
//                                color = Color.White.copy(alpha = 0.8f),
//                                fontSize = 13.sp
//                            )
                        }
                    }

                    // Stat Badges (Streak & Coins)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Streak Badge
                        StatBadge(icon = "🔥", value = "$streakDays")

                        // Coins Badge
//                        StatBadge(icon = "🪙", value = "$coins")

                        // Notification Icon
//                        IconButton(onClick = onNotificationClick) {
//                            Icon(
//                                imageVector = Icons.Outlined.Notifications,
//                                contentDescription = "Notifications",
//                                tint = textBlack
//                            )
//                        }
                    }
                }
                SmallHeightSpacer()
                // -------------------------------------------------------------
                // 2. HERO CARD: Daily Challenge / Quick Action Banner
                // -------------------------------------------------------------
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Box(
                            modifier = Modifier.background(color = amber, shape = RoundedCornerShape(20.dp))
                        ) {
                            BodyText(
                                text = "DAILY CHALLENGE",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                textColor = Color.White,
                                textSize = bodyFontTinySize,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            BodyText(
                                text = "General Knowledge",
                                textColor = textBlack,
                                textSize = bodyFontSize,
                                fontWeight = FontWeight.SemiBold
                            )

                            // CTA Play Button
                            ColoredButton(
                                buttonText = "Continue",
                                buttonColor = lightBlue,
                                roundness = 14.dp,
                                textColor = Color.White,
                                textSize = bodyFontSmallSize
                            ) {
                                onStartQuizClick()
                            }
//                            Button(
//                                onClick = onStartQuizClick,
//                                colors = ButtonDefaults.buttonColors(
//                                    lightBlue
//                                ),
//                                shape = RoundedCornerShape(14.dp)
//                            ) {
//                                BodyText(
//                                    text = "Continue",
//                                    textColor = Color.White,
//                                    textSize = bodyFontTinySize,
//                                    fontWeight = FontWeight.SemiBold
//                                )
//                            }
                        }

                        HeightSpacer()
//                        Spacer(modifier = Modifier.height(16.dp))

                        // Daily Progress Indicator
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            BodyText(
                                text = "Daily Goal Progress",
                                textColor = textBlack.copy(alpha = 0.8f),
                                textSize = bodyFontTinySize
                            )
                            BodyText(
                                text = "${(dailyProgress * 100).toInt()}%",
                                textColor = lightBlue,
                                textSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        SmallHeightSpacer()
//                        Spacer(modifier = Modifier.height(6.dp))

                        LinearProgressIndicator(
                            progress = { dailyProgress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = amber,
                            trackColor = Color.Gray.copy(alpha = 0.2f)
                        )
                    }
                }
            }
        }
    }
}

// Helper composable for small header badges
@Composable
private fun StatBadge(icon: String, value: String) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = Color.Black.copy(alpha = 0.25f)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = icon, fontSize = 12.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = value,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}