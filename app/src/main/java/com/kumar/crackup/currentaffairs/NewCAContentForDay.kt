package com.kumar.crackup.currentaffairs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kumar.crackup.R
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.templates.HeaderText
import com.kumar.crackup.templates.SmallHeightSpacer
import com.kumar.crackup.ui.theme.bodyFontExtraLargeSize
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.ui.theme.bodyFontSmallSize
import com.kumar.crackup.ui.theme.heroFontSize
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.textBlack
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// 1. Data model for the news item
data class NewsAffairsItem(
    val category: String,
    val categoryColor: Color,
    val icon: Int,
    val title: String,
    val readTime: String,
    val onClick: () -> Unit = {}
)

// 2. Reusable Card Component
@Composable
fun NewsAffairsCard(
    items: List<NewsAffairsItem>,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                expanded = !expanded
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Category Icon Badge (Circular)
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(items[0].categoryColor),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(items[0].icon),
                        contentDescription = items[0].category,
                        modifier = Modifier.size(24.dp)
                    )
                }

                // Central Content: Category Header, Title, and Meta info
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    // Top Row: Category Label and Read Time
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = items[0].category.uppercase(),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = items[0].categoryColor,
                            letterSpacing = 0.5.sp
                        )

                        Text(
                            text = "${items.size} Events",
                            fontSize = 10.sp,
                            color = Color(0xFF94A3B8)
                        )
                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    // Headline Title
//                    Text(
//                        text = item.title,
//                        fontSize = 13.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color(0xFF0F172A),
//                        lineHeight = 17.sp,
//                        maxLines = 2
//                    )
                }

                // Trailing Chevron Arrow
                Image(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Navigate",
                    modifier = Modifier.size(20.dp).rotate(if(expanded) 90f else -90f)
                )
            }

            AnimatedVisibility(
                visible = expanded
            ) {
                Column() {
                    items.forEach { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                                .clickable{
                                    onClick()
                                },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            // Category Icon Badge (Circular)
                            HeaderText(text = "100", textColor = item.categoryColor, textSize = bodyFontExtraLargeSize)

                            // Central Content: Category Header, Title, and Meta info
                            Row(
                                modifier = Modifier.fillMaxWidth(0.9f),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = item.title,
                                    fontSize = 13.sp,
                                    modifier = Modifier.fillMaxWidth(fraction = 0.75f),
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF0F172A),
                                    lineHeight = 17.sp,
                                    maxLines = 2
                                )

                                Text(
                                    text = item.readTime,
                                    fontSize = 10.sp,
                                    color = Color(0xFF94A3B8)
                                )
                            }

                            // Trailing Chevron Arrow
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = "Navigate",
                                tint = Color(0xFFCBD5E1),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }

    }
}

// 3. Usage Example List Container
@Composable
fun NewsAffairsList(navHostController: NavHostController, selectedDate: LocalDate) {
    val sampleNewsList = listOf(
        NewsAffairsItem(
            category = "National",
            categoryColor = Color(0xFF2563EB), // Blue
            icon = R.drawable.flag,
            title = "New Government Scheme Announced for Students",
            readTime = "2 min read"
        ),
        NewsAffairsItem(
            category = "Economy",
            categoryColor = Color(0xFF16A34A), // Green
            icon = R.drawable.rupee,
            title = "RBI Announces New Monetary Policy Measures",
            readTime = "3 min read"
        ),
        NewsAffairsItem(
            category = "International",
            categoryColor = Color(0xFF7C3AED), // Purple
            icon = R.drawable.world,
            title = "India Signs New Agreement with European Union",
            readTime = "2 min read"
        ),
        NewsAffairsItem(
            category = "Science & Tech",
            categoryColor = Color(0xFF0284C7), // Teal / Light Blue
            icon = R.drawable.rocket,
            title = "ISRO Successfully Launches New Communication Satellite",
            readTime = "3 min read"
        ),
        NewsAffairsItem(
            category = "Sports",
            categoryColor = Color(0xFFEA580C), // Orange
            icon = R.drawable.trophy,
            title = "India Wins Gold in Asian Games 2026",
            readTime = "1 min read"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
//            .background(Color(0xFFF8FAFC))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Column {
            BodyText(
                text = selectedDate.format(DateTimeFormatter.ofPattern("d MMMM yyyy")),
                textSize = bodyFontLargeSize,
                textColor = lightBlue,
                fontWeight = FontWeight.Bold,
            )
            BodyText(
                text = "${3 ?: 0} Important News",
                textSize = bodyFontSmallSize,
                textColor = textBlack,
            )
        }
        SmallHeightSpacer()
        sampleNewsList.forEach { news ->
            NewsAffairsCard(items = listOf(news)) {
                navHostController.navigate("ca_details")
            }
        }
    }
}