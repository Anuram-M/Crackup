package com.kumar.crackup.currentaffairs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kumar.crackup.templates.FeatureCarouselCard
import com.kumar.crackup.templates.HeaderText
import com.kumar.crackup.templates.HeightSpacer
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.util.AppContentUtil

@Composable
fun Entry(navHostController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp)) {
            HeaderText(text = "Current Affairs", textColor = lightBlue, textSize = bodyFontLargeSize)
            HeightSpacer()
            AppContentUtil.currentAffairsCategories.forEach { item ->
                CACategoryCard(item) {
                    navHostController.navigate(item.category)
                }
            }
        }

    }
}