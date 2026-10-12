package com.kumar.crackup.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kumar.crackup.ui.theme.bodyFontSize

@Composable
fun SelectorTextRadioButton(
    isSelected: Boolean,
    selectorText: String,
    selectedColor: Color,
    unselectedColor: Color,
    modifier: Modifier = Modifier,
    onSelected: () -> Unit,

    ) {
    Box(
        modifier = modifier
            .padding(10.dp)
            .size(32.dp)
            .background( shape = CircleShape, color =  if(isSelected) selectedColor else Color.Transparent)
            .border(width = 1.dp, shape = CircleShape, color = if(isSelected) selectedColor else unselectedColor )
            .clickable {
                onSelected()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = selectorText, textAlign = TextAlign.Center,
            style = TextStyle(
                color = if(isSelected) Color.White else selectedColor,
                fontSize = bodyFontSize,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}