package com.kumar.crackup.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kumar.crackup.model.InfoItem
import com.kumar.crackup.templates.InfoCard
import com.kumar.crackup.ui.theme.BaseAppTheme
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.screenBackground

@Composable
fun AboutComposable() {

    val infoItems = listOf(
        InfoItem(
            title = "🌟 Our Mission",
            description = "At CrackUp, our mission is to provide accessible, exam-oriented, and practice-driven learning for TNPSC, UPSC, school students, and competitive exam aspirants. We focus on delivering high-quality MCQs, syllabus-aligned content, and clear explanations that strengthen fundamentals, improve accuracy, and build confidence through consistent proctice."
        ),
        InfoItem(
            title = "🔭 Our Vision",
            description = "\tTo become a trusted and learner-centric exam preparation platform that empowers aspirants across India to achieve success in competitive examinations through clarity, consistency, and quality content."
        ),
        InfoItem(
            title = "💎 Our Core Values",
            description = "• Quality over quantity of MCQ questions\n• Practice with Purpose of improvement\n• Accessibility & Inclusion with different packages\n• Continuous Learning for growth\n• Integrity & Trust with honest guidance"
        ),
    )

    Box(modifier = Modifier.fillMaxSize().padding(10.dp).background(screenBackground)) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Text(
                text = "Our Mission, Vision & Values",
                textAlign = TextAlign.Start,
                style = TextStyle(
                    color = lightBlue,
                    fontSize = bodyFontLargeSize,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            )

            infoItems.forEach {
                InfoCard(it)
            }
        }
    }
}

@Composable
@Preview
fun previewAbout() {
    BaseAppTheme {
        AboutComposable()
    }
}