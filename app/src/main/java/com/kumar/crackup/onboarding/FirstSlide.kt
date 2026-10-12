package com.kumar.crackup.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kumar.crackup.templates.SmallHeightSpacer
import com.kumar.crackup.templates.StatsCard
import com.kumar.crackup.ui.theme.BaseAppTheme
import com.kumar.crackup.ui.theme.bodyFont
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.ui.theme.bodyFontSize
import com.kumar.crackup.ui.theme.heroFontLargeSize
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.titanFont
import com.kumar.crackup.util.AppContentUtil.stats

@Composable
fun FirstSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(10.dp),
        ) {
            Text(
                text = "Welcome to ",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = bodyFontSize,
                    fontFamily = bodyFont,
                    fontWeight = FontWeight.Medium,
                ),
                modifier = Modifier.padding(start = 10.dp, bottom = 3.dp)
            )
            Text(
                text = "CrackUp",
                style = TextStyle(
                    color = lightBlue,
                    fontSize = heroFontLargeSize,
                    fontFamily = titanFont,
                    letterSpacing = 3.sp,
                    lineHeight = heroFontLargeSize,
                    fontWeight = FontWeight.Bold,
                ),
            )
            SmallHeightSpacer()
            Text(
                text = "where you can crack Government Exams with Confidence 🚀",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = bodyFontSize,
                    fontFamily = bodyFont,
                    fontWeight = FontWeight.Medium,
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )
        Text(
            text = "Why choose CrackUp?",
            style = TextStyle(
                color = lightBlue,
                fontSize = bodyFontLargeSize,
                fontFamily = bodyFont,
                fontWeight = FontWeight.Bold,
            ),
        )
        StatsCard(
            stats = stats,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )
    }
}

@Preview
@Composable
fun PreviewFirstSlide() {
    BaseAppTheme {
        FirstSlide()
    }
}