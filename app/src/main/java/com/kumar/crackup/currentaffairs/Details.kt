package com.kumar.crackup.currentaffairs
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.templates.ColoredButton
import com.kumar.crackup.templates.HeaderText
import com.kumar.crackup.ui.theme.bodyFont
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.ui.theme.bodyFontSmallSize
import com.kumar.crackup.ui.theme.lightBlue

// 1. Data Model representing the detail article layout
data class ArticleDetailModel(
    val category: String,
    val title: String,
    val date: String,
    val readTime: String,
    val imageUrl: String,
    val whatsNewText: String,
    val keyHighlights: List<String>,
    val importanceText: String
)

// Primary blue color matching the UI
private val BrandBlue = Color(0xFF1D61E7)
private val DarkText = Color(0xFF0F172A)
private val SecondaryText = Color(0xFF64748B)

// 2. Main Template Component
@Composable
fun ArticleDetailScreen(
    article: ArticleDetailModel,
    onShareClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    onTakeQuizClick: () -> Unit = {}
) {
    Scaffold(
        bottomBar = {
            ArticleBottomActions(
                onShareClick = onShareClick,
                onSaveClick = onSaveClick,
                onTakeQuizClick = onTakeQuizClick
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Category Badge
            Surface(
                color = BrandBlue,
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = article.category.uppercase(),
                    color = Color.White,
                    fontFamily = bodyFont,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Main Title
            BodyText(
                text = article.title,
                textSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textColor = DarkText,
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Metadata Row (Date & Read Time)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
//                MetaIconText(icon = Icons.Outlined.CalendarToday, text = article.date)
//                MetaIconText(icon = Icons.Outlined.Schedule, text = article.readTime)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Featured Image Banner
            AsyncImage(
                model = article.imageUrl,
                contentDescription = article.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFE2E8F0))
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Section 1: What's New?
            SectionHeader(text = "What's New?")
            Spacer(modifier = Modifier.height(6.dp))
            BodyText(
                text = article.whatsNewText,
                textSize = bodyFontSmallSize,
                textColor = DarkText,
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Section 2: Key Highlights
            SectionHeader(text = "Key Highlights")
            Spacer(modifier = Modifier.height(8.dp))
            article.keyHighlights.forEach { highlight ->
                BodyText(
                    text = "• ${highlight}",
                    textSize = bodyFontSmallSize,
                    textColor = DarkText,
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Section 3: Why is it Important?
            SectionHeader(text = "Why is it Important?")
            Spacer(modifier = Modifier.height(6.dp))
             BodyText(
                text = article.importanceText,
                textSize = bodyFontSmallSize,
                textColor = DarkText,
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// 3. Helper Composables
@Composable
private fun MetaIconText(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = SecondaryText,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = text,
            fontSize = 12.sp,
            color = SecondaryText,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun SectionHeader(text: String) {
    HeaderText(
        text = text,
        textSize = bodyFontLargeSize,
        textColor = BrandBlue
    )
}

@Composable
private fun ArticleBottomActions(
    onShareClick: () -> Unit,
    onSaveClick: () -> Unit,
    onTakeQuizClick: () -> Unit
) {
    Surface(
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Share Button
//            OutlinedButton(
//                onClick = onShareClick,
//                modifier = Modifier
//                    .weight(1f)
//                    .height(48.dp),
//                shape = RoundedCornerShape(10.dp),
//                border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
//                colors = ButtonDefaults.outlinedButtonColors(
//                    containerColor = Color(0xFFF8FAFC),
//                    contentColor = DarkText
//                )
//            ) {
//                Icon(
//                    imageVector = Icons.Outlined.Share,
//                    contentDescription = "Share",
//                    modifier = Modifier.size(18.dp)
//                )
//                Spacer(modifier = Modifier.width(6.dp))
//                Text("Share", fontWeight = FontWeight.Bold, fontSize = 13.sp)
//            }
//
//            // Save Button
//            OutlinedButton(
//                onClick = onSaveClick,
//                modifier = Modifier
//                    .weight(1f)
//                    .height(48.dp),
//                shape = RoundedCornerShape(10.dp),
//                border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
//                colors = ButtonDefaults.outlinedButtonColors(
//                    containerColor = Color.White,
//                    contentColor = DarkText
//                )
//            ) {
//                Text("Save", fontWeight = FontWeight.Bold, fontSize = 13.sp)
//            }

            // Take Quiz Button
            ColoredButton(
                roundness = 10.dp,
                buttonText = "Take Quiz ->",
                buttonColor = lightBlue,
                textColor = Color.White,
                textSize = bodyFontSmallSize
            ) { onTakeQuizClick() }
        }
    }
}

// 4. Sample Usage
@Composable
fun ArticleDetailPreview() {
    val sampleArticle = ArticleDetailModel(
        category = "National",
        title = "New Government Scheme Announced for Students",
        date = "2 September 2026",
        readTime = "2 min read",
        imageUrl = "https://picsum.photos/300/200",
        whatsNewText = "The Government of India has announced a new scheme aimed at providing financial assistance and better learning opportunities to students across the country.",
        keyHighlights = listOf(
            "Financial aid up to ₹10,000 for eligible students.",
            "Support for higher education and skill development.",
            "Special focus on underprivileged communities.",
            "Easy online application process."
        ),
        importanceText = "This scheme will help lakhs of students continue their education without worrying about financial constraints."
    )

    ArticleDetailScreen(article = sampleArticle)
}
