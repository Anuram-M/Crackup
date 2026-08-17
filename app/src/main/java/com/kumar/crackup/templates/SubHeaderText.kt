package com.kumar.crackup.templates

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.kumar.crackup.ui.theme.bodyFont
import com.kumar.crackup.ui.theme.bodyFontSize
import com.kumar.crackup.ui.theme.lightBlue

@Composable
fun SubHeaderText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = TextStyle(
            color = lightBlue,
            fontSize = bodyFontSize,
            fontFamily = bodyFont,
            fontWeight = FontWeight.SemiBold,
        ),
        modifier = modifier
    )
}