package com.kumar.crackup.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GlasmorphicCard(content: String, textColor: Color = Color.White) {
    val cardShape = RoundedCornerShape(10.dp)

    Box(modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp,)) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .matchParentSize()
//                .height(80.dp)
                .clip(cardShape)
                .blur(radius = 16.dp)
                .background(textColor.copy(alpha = 0.12f))
                .border(
                    width = 1.dp,
                    color = textColor.copy(alpha = 0.25f),
                    shape = cardShape
                )
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.CenterStart
        ) {}
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BodyText(
                text = content, modifier = Modifier, textColor = textColor
            )
        }
    }

}