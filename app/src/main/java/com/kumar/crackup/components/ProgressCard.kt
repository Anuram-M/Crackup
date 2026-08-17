package com.kumar.crackup.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.templates.ColoredButton
import com.kumar.crackup.templates.HeaderText
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.ui.theme.lightBlue

@Composable
fun ProgressCard() {

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(modifier = Modifier.fillMaxWidth().padding(10.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
            Column() {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 10.dp, top = 10.dp, end = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        HeaderText(text = "0 Days", modifier = Modifier, textSize = bodyFontLargeSize)
                        BodyText(text = "🔥 Streak", modifier = Modifier, fontWeight = FontWeight.Bold)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        HeaderText(text = "0", modifier = Modifier, textSize = bodyFontLargeSize)
                        BodyText(text = "🎯 Tests Taken", modifier = Modifier, fontWeight = FontWeight.Bold)
                    }
//                Image(painter = painterResource(R.drawable.about_us), contentDescription = null)
//                Image(painter = painterResource(R.drawable.about_us), contentDescription = null)
//                Image(painter = painterResource(R.drawable.about_us), contentDescription = null)
                }
                ColoredButton(buttonText = "Take a Test", buttonColor = lightBlue, textColor = Color.White) { }
            }

        }
    }

}