package com.kumar.crackup.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kumar.crackup.templates.GradientButton
import com.kumar.crackup.templates.HeaderText
import com.kumar.crackup.ui.theme.linearGradient1e

@Composable
fun PlanCard(navHostController: NavHostController) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HeaderText(text = "Free Plan", modifier = Modifier.weight(1f))
                GradientButton(
                    onButtonClick = {navHostController.navigate("subscription")},
                    buttonText = "Upgrade",
                    textColor = Color.White,
                    gradientColors = linearGradient1e,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}