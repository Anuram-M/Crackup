package com.kumar.crackup.templates

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.kumar.crackup.ui.theme.bodyFont
import com.kumar.crackup.ui.theme.bodyFontSize
import com.kumar.crackup.ui.theme.lightBlue

@Composable
fun HeaderText(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = lightBlue,
    textSize: TextUnit = bodyFontSize
) {
    Text(
        text = text,
        style = TextStyle(
            color = textColor,
            fontSize = textSize,
            fontFamily = bodyFont,
            fontWeight = FontWeight.Bold,
        ),
        modifier = modifier
    )
}