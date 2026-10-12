package com.kumar.crackup.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.kumar.crackup.ui.theme.bodyFont
import com.kumar.crackup.ui.theme.bodyFontSize

@Composable
fun ColoredButton(
    roundness: Dp = 100.dp,
    buttonText: String,
    buttonColor: Color,
    textColor: Color,
    textSize: TextUnit = bodyFontSize,
    fontWeight: FontWeight = FontWeight.Bold,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,

    ) {
    Box(
        modifier = modifier
            .background( shape = RoundedCornerShape(roundness), color = buttonColor)

            .clickable {
                onButtonClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = buttonText, textAlign = TextAlign.Center,
            style = TextStyle(
                fontFamily = bodyFont,
                color = textColor,
                fontSize = textSize,
                fontWeight = fontWeight
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}