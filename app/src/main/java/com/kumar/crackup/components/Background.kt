package com.kumar.crackup.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kumar.crackup.ui.theme.BaseAppTheme

@Composable
fun Background(
    modifier: Modifier = Modifier,
    headerHeight: Dp = 320.dp
) {
    val topLightBlue = Color(0xFFEEF2FC) // Top background color
    val iconColor = Color(0xFF2561E6)    // Requested doodle icon color

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(headerHeight)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            // 1. Draw Background Gradient (#EEF2FC -> White)
            drawRect(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to topLightBlue,
                        0.45f to topLightBlue,
                        0.80f to topLightBlue.copy(alpha = 0.4f),
                        1.0f to Color.White
                    )
                ),
                size = size
            )

            val strokeStyle = Stroke(width = 2.5.dp.toPx())

            // 2. Draw Doodle Icons Across Bottom Pattern

            // --- Globe (Right) ---
            drawGlobe(
                center = Offset(width * 0.78f, height * 0.70f),
                radius = 45.dp.toPx(),
                color = iconColor.copy(alpha = 0.85f),
                style = strokeStyle
            )

            // --- Graduation Cap (Center Left) ---
            drawGradCap(
                center = Offset(width * 0.35f, height * 0.82f),
                size = 50.dp.toPx(),
                color = iconColor.copy(alpha = 0.85f),
                style = strokeStyle
            )

            // --- Light Bulb (Center Right) ---
            drawLightBulb(
                center = Offset(width * 0.58f, height * 0.65f),
                size = 35.dp.toPx(),
                color = iconColor.copy(alpha = 0.75f),
                style = strokeStyle
            )

            // --- Open Book (Left) ---
            drawOpenBook(
                center = Offset(width * 0.22f, height * 0.72f),
                widthPx = 55.dp.toPx(),
                heightPx = 38.dp.toPx(),
                color = iconColor.copy(alpha = 0.8f),
                style = strokeStyle
            )

            // --- Exam Sheet / Document (Far Right) ---
            drawExamSheet(
                topLeft = Offset(width * 0.68f, height * 0.82f),
                widthPx = 42.dp.toPx(),
                heightPx = 52.dp.toPx(),
                color = iconColor.copy(alpha = 0.8f),
                style = strokeStyle
            )

            // --- Background Gears & Stars (Fills Empty Spaces) ---
            drawStar(Offset(width * 0.90f, height * 0.42f), 14.dp.toPx(), iconColor.copy(alpha = 0.5f), strokeStyle)
            drawStar(Offset(width * 0.30f, height * 0.52f), 10.dp.toPx(), iconColor.copy(alpha = 0.4f), strokeStyle)
            drawStar(Offset(width * 0.12f, height * 0.78f), 12.dp.toPx(), iconColor.copy(alpha = 0.6f), strokeStyle)

            // Gear Badge
            drawCircle(
                color = iconColor.copy(alpha = 0.6f),
                radius = 14.dp.toPx(),
                center = Offset(width * 0.12f, height * 0.55f),
                style = strokeStyle
            )
        }
    }
}

// ==========================================
// DrawScope Helper Functions for Icons
// ==========================================

private fun DrawScope.drawGlobe(center: Offset, radius: Float, color: Color, style: Stroke) {
    drawCircle(color = color, radius = radius, center = center, style = style)
    drawOval(color = color, topLeft = Offset(center.x - radius * 0.45f, center.y - radius), size = Size(radius * 0.9f, radius * 2f), style = style)
    drawLine(color = color, start = Offset(center.x - radius, center.y), end = Offset(center.x + radius, center.y), strokeWidth = style.width)
    drawLine(color = color, start = Offset(center.x - radius * 0.85f, center.y - radius * 0.45f), end = Offset(center.x + radius * 0.85f, center.y - radius * 0.45f), strokeWidth = style.width)
    drawLine(color = color, start = Offset(center.x - radius * 0.85f, center.y + radius * 0.45f), end = Offset(center.x + radius * 0.85f, center.y + radius * 0.45f), strokeWidth = style.width)
}

private fun DrawScope.drawGradCap(center: Offset, size: Float, color: Color, style: Stroke) {
    val capPath = Path().apply {
        moveTo(center.x, center.y - size * 0.5f)
        lineTo(center.x + size * 0.8f, center.y - size * 0.1f)
        lineTo(center.x, center.y + size * 0.3f)
        lineTo(center.x - size * 0.8f, center.y - size * 0.1f)
        close()
    }
    drawPath(path = capPath, color = color, style = style)

    val skullPath = Path().apply {
        moveTo(center.x - size * 0.45f, center.y + size * 0.1f)
        lineTo(center.x - size * 0.45f, center.y + size * 0.5f)
        quadraticTo(center.x, center.y + size * 0.75f, center.x + size * 0.45f, center.y + size * 0.5f)
        lineTo(center.x + size * 0.45f, center.y + size * 0.1f)
    }
    drawPath(path = skullPath, color = color, style = style)
}

private fun DrawScope.drawOpenBook(center: Offset, widthPx: Float, heightPx: Float, color: Color, style: Stroke) {
    val leftBook = Path().apply {
        moveTo(center.x, center.y + heightPx / 2)
        quadraticTo(center.x - widthPx / 4, center.y + heightPx / 3, center.x - widthPx / 2, center.y + heightPx / 2)
        lineTo(center.x - widthPx / 2, center.y - heightPx / 2)
        quadraticTo(center.x - widthPx / 4, center.y - heightPx * 0.65f, center.x, center.y - heightPx / 3)
        close()
    }
    val rightBook = Path().apply {
        moveTo(center.x, center.y + heightPx / 2)
        quadraticTo(center.x + widthPx / 4, center.y + heightPx / 3, center.x + widthPx / 2, center.y + heightPx / 2)
        lineTo(center.x + widthPx / 2, center.y - heightPx / 2)
        quadraticTo(center.x + widthPx / 4, center.y - heightPx * 0.65f, center.x, center.y - heightPx / 3)
        close()
    }
    drawPath(path = leftBook, color = color, style = style)
    drawPath(path = rightBook, color = color, style = style)
}

private fun DrawScope.drawLightBulb(center: Offset, size: Float, color: Color, style: Stroke) {
    drawCircle(color = color, radius = size * 0.5f, center = Offset(center.x, center.y - size * 0.2f), style = style)
    drawLine(color = color, start = Offset(center.x - size * 0.25f, center.y + size * 0.4f), end = Offset(center.x + size * 0.25f, center.y + size * 0.4f), strokeWidth = style.width)
    drawLine(color = color, start = Offset(center.x - size * 0.18f, center.y + size * 0.55f), end = Offset(center.x + size * 0.18f, center.y + size * 0.55f), strokeWidth = style.width)
}

private fun DrawScope.drawExamSheet(topLeft: Offset, widthPx: Float, heightPx: Float, color: Color, style: Stroke) {
    drawRoundRect(color = color, topLeft = topLeft, size = Size(widthPx, heightPx), style = style)
    drawLine(color = color, start = Offset(topLeft.x + widthPx * 0.2f, topLeft.y + heightPx * 0.25f), end = Offset(topLeft.x + widthPx * 0.8f, topLeft.y + heightPx * 0.25f), strokeWidth = style.width)
    drawLine(color = color, start = Offset(topLeft.x + widthPx * 0.2f, topLeft.y + heightPx * 0.45f), end = Offset(topLeft.x + widthPx * 0.8f, topLeft.y + heightPx * 0.45f), strokeWidth = style.width)
    drawLine(color = color, start = Offset(topLeft.x + widthPx * 0.2f, topLeft.y + heightPx * 0.65f), end = Offset(topLeft.x + widthPx * 0.55f, topLeft.y + heightPx * 0.65f), strokeWidth = style.width)
}

private fun DrawScope.drawStar(center: Offset, radius: Float, color: Color, style: Stroke) {
    val path = Path()
    for (i in 0 until 10) {
        val r = if (i % 2 == 0) radius else radius * 0.4f
        val angle = Math.toRadians((i * 36 - 90).toDouble())
        val x = (center.x + r * Math.cos(angle)).toFloat()
        val y = (center.y + r * Math.sin(angle)).toFloat()
        if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
    }
    path.close()
    drawPath(path = path, color = color, style = style)
}

@Preview
@Composable
fun previewBackground() {
    BaseAppTheme() {
        Background()
    }
}