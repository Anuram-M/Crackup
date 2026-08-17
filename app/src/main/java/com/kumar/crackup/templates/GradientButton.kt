package com.kumar.crackup.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kumar.crackup.ui.theme.bodyFontSize

@Composable
fun GradientButton(
    buttonText: String,
    gradientColors: List<Color>,
    textColor: Color,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,

    ) {
    Box(
        modifier = modifier
            .padding(horizontal = 10.dp, vertical = 15.dp)
            .shadow(elevation = 5.dp, shape =RoundedCornerShape(100.dp), )
            .background( shape = RoundedCornerShape(100.dp), brush = Brush.linearGradient(gradientColors))

            .clickable {
                onButtonClick()
            }
    ) {
        Text(
            text = buttonText, textAlign = TextAlign.Center,
            style = TextStyle(
                color = textColor,
                fontSize = bodyFontSize,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
    }
}