package com.kumar.crackup.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kumar.crackup.R
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.templates.SmallWidthSpacer
import com.kumar.crackup.ui.theme.bodyFontSize
import com.kumar.crackup.ui.theme.lightBlue
import kotlinx.coroutines.delay

@Composable
fun TimerComponent(
    totalSeconds: Long,
    onFinished: () -> Unit
) {
    var timeLeft by remember { mutableLongStateOf(totalSeconds) }

    LaunchedEffect(timeLeft) {
        if(timeLeft > 0 ) {
            delay(1000)
            timeLeft--
        } else {
            onFinished()
        }
    }

    val hours = timeLeft / 3600
    val remaining = timeLeft % 3600
    val minutes = remaining / 60
    val seconds = remaining % 60

    val time = String.format("%02d:%02d:%02d", hours, minutes, seconds)

    val contentColor = if(timeLeft < 600) Color.Red else lightBlue
    Box(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp), contentAlignment = Alignment.TopCenter) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(R.drawable.clock),
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = contentColor
            )
            SmallWidthSpacer()
            BodyText(text = time, textColor = contentColor, textSize = bodyFontSize)
        }

    }

}