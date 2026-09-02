package com.kumar.crackup.templates

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kumar.crackup.model.InfoItem
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.ui.theme.bodyFontSmallSize
import com.kumar.crackup.ui.theme.lightBlue

@Composable
fun InfoCard(infoItem: InfoItem) {

    Card(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
        ) {
            Text(
                text = infoItem.title,
                textAlign = TextAlign.Start,
                style = TextStyle(
                    color = lightBlue,
                    fontSize = bodyFontLargeSize,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
            )
            Text(
                text = infoItem.description,
                textAlign = TextAlign.Start,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = bodyFontSmallSize,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}