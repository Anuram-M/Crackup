package com.kumar.crackup.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LearnerCard() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(modifier = Modifier.fillMaxWidth().height(100.dp).padding(10.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {

        }
    }
}