package com.kumar.crackup.templates

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kumar.crackup.model.ExamItem

@Composable
fun ExamCard(examItem: ExamItem = ExamItem()) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
                SubHeaderText(text = examItem.title, modifier = Modifier
                    .fillMaxWidth())
                Spacer(modifier = Modifier.height(5.dp))
                BodyText(text = examItem.description, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(5.dp))
                examItem.bullets.forEach {
                    Row() {
                        BodyText(text = it.heading, fontWeight = FontWeight.Bold, modifier = Modifier )
                        Spacer(modifier = Modifier.width(5.dp))
                        BodyText(text = it.exams,modifier = Modifier )
                    }
                }
            }
        }
    }
}