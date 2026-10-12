package com.kumar.crackup.templates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
import com.kumar.crackup.model.StatItem
import com.kumar.crackup.ui.theme.bodyFont
import com.kumar.crackup.ui.theme.bodyFontExtraLargeSize
import com.kumar.crackup.ui.theme.bodyFontSmallSize
import com.kumar.crackup.ui.theme.lightBlue

@Composable
fun StatsCard(stats: List<StatItem>, modifier: Modifier = Modifier) {



    Box(
        modifier = modifier
            .heightIn(min = 100.dp)

    ) {
        Card(
            modifier = Modifier.matchParentSize(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                stats.forEach {
                    Column(modifier = Modifier.fillMaxHeight().weight(1f), verticalArrangement = Arrangement.Center) {
                        Text(
                            text = it.stat,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = lightBlue,
                                fontFamily = bodyFont,
                                fontSize = bodyFontExtraLargeSize,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = it.description,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Gray,
                                fontSize = bodyFontSmallSize,
                                fontWeight = FontWeight.Normal
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}