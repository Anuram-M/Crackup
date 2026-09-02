package com.kumar.crackup.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kumar.crackup.templates.FeatureCarouselCard
import com.kumar.crackup.templates.HeaderText
import com.kumar.crackup.templates.SmallHeightSpacer
import com.kumar.crackup.ui.theme.BaseAppTheme
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.util.AppContentUtil

@Composable
fun SecondSlide() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HeaderText(
            text = "What you get from Us?",
            textSize = bodyFontLargeSize)
        SmallHeightSpacer()
        FlowRow(
            modifier = Modifier
                .padding(10.dp),
        ) {
            AppContentUtil.features.forEach {
                FeatureCarouselCard(it)
            }
        }
    }
}

@Preview
@Composable
fun PreviewSecondSlide() {
    BaseAppTheme {
        SecondSlide()
    }
}