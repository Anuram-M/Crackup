package com.kumar.crackup.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kumar.crackup.model.ServiceModel
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.templates.ColoredButton
import com.kumar.crackup.templates.HeaderText
import com.kumar.crackup.templates.SubHeaderText
import com.kumar.crackup.ui.theme.bodyFontTinySize
import com.kumar.crackup.ui.theme.darkBlue
import com.kumar.crackup.ui.theme.dialogCardColor
import com.kumar.crackup.ui.theme.heroFontSize
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.textBlack

@Composable
fun PayPackageCard(currentPack: ServiceModel, bestPack: ServiceModel, onCurrentPackSelected: () -> Unit, onBestPackSelected: () -> Unit) {

    Row(
        modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f).padding(5.dp)
            .background(dialogCardColor)
            .border(width = 1.dp, color = textBlack, shape = RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center) {
                Column(modifier = Modifier.padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    BodyText(text = currentPack.title, modifier = Modifier)
                    HeaderText(text = currentPack.price,
                        textColor = textBlack,
                        textSize = heroFontSize
                            )
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 10.dp), color = Color.LightGray)
                    Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp, horizontal = 10.dp)) {

                        BodyText("Full Access to:")
                        currentPack.includedFeatures.forEach {
                            BodyText(text = it, textColor = textBlack, textSize = bodyFontTinySize)
                        }
                    }

                    ColoredButton(
                        roundness = 10.dp,
                        modifier = Modifier.fillMaxWidth(),
                        buttonText = "Continue",
                        textColor = dialogCardColor,
                        buttonColor = textBlack
                    ) { }
                }
        }
        Box(modifier = Modifier.weight(1f).padding(5.dp)
            .background(dialogCardColor)
            .border(width = 1.dp, color = lightBlue, shape = RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center) {
                Column(modifier = Modifier.padding(5.dp),horizontalAlignment = Alignment.CenterHorizontally) {
                    BodyText(text = bestPack.title, modifier = Modifier)
                    HeaderText(text = bestPack.price,
                        textColor = textBlack,
                        textSize = heroFontSize
                            )
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 10.dp), color = Color.LightGray)
                    Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp, horizontal = 10.dp)) {

                        BodyText("Full Access to:")
                        bestPack.includedFeatures.forEach {
                            BodyText(text = it, textColor = textBlack, textSize = bodyFontTinySize)
                        }
                    }

                    ColoredButton(
                        roundness = 10.dp,
                        modifier = Modifier.fillMaxWidth(),
                        buttonText = "Upgrade",
                        textColor = dialogCardColor,
                        buttonColor = lightBlue
                    ) { }
                }
        }
    }
}