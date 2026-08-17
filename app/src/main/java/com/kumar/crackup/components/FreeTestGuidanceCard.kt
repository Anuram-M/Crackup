package com.kumar.crackup.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.templates.GradientButton
import com.kumar.crackup.templates.HeaderText
import com.kumar.crackup.ui.theme.linearGradient1e
import com.kumar.crackup.ui.theme.linearGradient2

@Composable
fun FreeTestGuidanceCard() {


    Box(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
        Card(modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {

            Box(
                modifier = Modifier.fillMaxWidth()
                    .background(brush = Brush.linearGradient(colors = linearGradient1e), shape = RoundedCornerShape(10))
                    .padding(10.dp),
            ) {
                Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 0.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    HeaderText(text = "🔥 Free TNPSC Mock Test", modifier = Modifier)
                    BodyText(
                        text = "With instant rankings", modifier = Modifier.align(
                            Alignment.CenterHorizontally
                        ), textColor = Color.White
                    )
                    GradientButton(
                        buttonText = "Start Free Test",
                        gradientColors = linearGradient2,
                        textColor = Color.White
                    ) { }
                }

            }
        }
    }
}