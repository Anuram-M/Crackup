package com.kumar.crackup.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun OutlineContainer(
    roundness: Dp= 20.dp,
    isEnabled: Boolean = true,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    contentColor: Color,
    onclicK: () -> Unit,
    content: @Composable () -> Unit
) {

    Box(modifier = modifier
        .background(color = backgroundColor, shape = RoundedCornerShape(roundness))
        .border(width = 1.dp,
        color = if(isEnabled) contentColor else Color.Gray, shape = RoundedCornerShape(roundness))
        .clickable{
            onclicK()
        },
        contentAlignment = Alignment.Center) {
        content()
    }

}