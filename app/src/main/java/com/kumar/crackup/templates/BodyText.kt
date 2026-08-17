package com.kumar.crackup.templates


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.unit.TextUnit
import com.kumar.crackup.ui.theme.bodyFont
import com.kumar.crackup.ui.theme.bodyFontSmallSize
import com.kumar.crackup.ui.theme.textBlack

@Composable
fun BodyText(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = textBlack,
    fontWeight: FontWeight = FontWeight.Normal,
    textSize: TextUnit = bodyFontSmallSize,
    isHtmlText: Boolean = false
) {
    Text(
        text = if(isHtmlText) AnnotatedString.fromHtml(text).toString() else text,
        style = TextStyle(
            color = textColor,
            fontSize = textSize,
            fontFamily = bodyFont,
            fontWeight = fontWeight,
        ),
        modifier = modifier
    )
}