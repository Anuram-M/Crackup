package com.kumar.crackup.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kumar.crackup.model.FeatureItem
import com.kumar.crackup.ui.theme.BaseAppTheme
import com.kumar.crackup.ui.theme.bodyFont
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.ui.theme.bodyFontTinySize
import com.kumar.crackup.ui.theme.heroFont
import com.kumar.crackup.ui.theme.heroFontLargeSize
import com.kumar.crackup.ui.theme.heroFontSize
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.textBlack
import com.kumar.crackup.ui.theme.textBlue

@Composable
fun FeatureCarouselCard(featureItem: FeatureItem = FeatureItem()) {

    Box(
        modifier = Modifier
            .heightIn(min = 180.dp)
            .background(Color.Transparent)
            .widthIn(min = 160.dp)

            .padding(5.dp)
    ) {
        Card(
            modifier = Modifier
                .matchParentSize(),
//                .border(width = 1.dp, color = lightBlue, shape = RoundedCornerShape(20.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)

        ) {
            Column(
                modifier = Modifier
//                    .background(Color.Blue, shape = RoundedCornerShape(20.dp))
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = featureItem.emoji,
                    style = TextStyle(
                        color = textBlue,
                        fontFamily = heroFont,
                        fontSize = heroFontSize,
                        fontWeight = FontWeight.Normal
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = featureItem.title,
                    style = TextStyle(
                        color = lightBlue,
                        fontFamily = bodyFont,
                        fontSize = bodyFontLargeSize,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = featureItem.subTitle,
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = textBlack,
                        fontFamily = bodyFont,
                        fontSize = bodyFontTinySize,
                        fontWeight = FontWeight.Normal
                    ),
                )
            }
        }
    }
}

@Composable
@Preview
fun previewFeatureCard() {
    BaseAppTheme  {
        FeatureCarouselCard()
    }
}