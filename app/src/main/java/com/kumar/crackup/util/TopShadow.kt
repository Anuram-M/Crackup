package com.kumar.crackup.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.topShadow(
    shadowHeight: Dp = 8.dp,
    alpha: Float = 0.07f
): Modifier = this.drawWithContent {
    // 1. Draw the actual content of the bottom bar first
    drawContent()

    // 2. Draw the top shadow brush immediately above the bounds
    val shadowHeightPx = shadowHeight.toPx()
    drawRect(
        brush = Brush.verticalGradient(
            colors = listOf(
                Color.Black.copy(alpha = alpha),
                Color.Transparent
            ),
            // Start at the very top edge and paint upwards
            startY = 0f,
            endY = -shadowHeightPx
        ),
        topLeft = Offset(0f, -shadowHeightPx),
        size = Size(size.width, shadowHeightPx)
    )
}