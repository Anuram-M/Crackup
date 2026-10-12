package com.kumar.crackup.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.templates.HeaderText
import com.kumar.crackup.templates.HeightSpacer
import com.kumar.crackup.ui.theme.lightBlue

/**
 * A versatile "Coming Soon" or "Empty State" component.
 * It will expand to fill the available space of its parent.
 */
@Composable
fun ComingSoonView(
    modifier: Modifier = Modifier,
    title: String = "Under Construction",
    subtitle: String = "This section is currently being engineered. Check back soon.",
    icon: ImageVector = Icons.Outlined.Build
) {
    // A dashed path effect to give it a "blueprint / under construction" feel
    val dashedBorder = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(0.85f),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth() // Keeps text from stretching too wide on tablets
//                    .border(
//                        width = 2.dp,
//                        color = MaterialTheme.colorScheme.outlineVariant,
//                        shape = RoundedCornerShape(16.dp)
//                    )
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Icon
                Icon(
                    imageVector = icon,
                    contentDescription = "Under Construction Icon",
                    modifier = Modifier.size(64.dp),
                    tint = lightBlue
                )

                HeightSpacer()
//                Spacer(modifier = Modifier.height(24.dp))

                // Title
                HeaderText(text = title,
                    textColor = lightBlue,
                )
//                Text(
//                    text = title,
//                    style = MaterialTheme.typography.headlineSmall,
//                    color = MaterialTheme.colorScheme.onSurface,
//                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
//                    textAlign = TextAlign.Center
//                )

                HeightSpacer()
//                Spacer(modifier = Modifier.height(12.dp))

                // Subtitle
                BodyText(text = subtitle, modifier = Modifier.fillMaxWidth())
//                Text(
//                    text = subtitle,
//                    style = MaterialTheme.typography.bodyMedium,
//                    color = MaterialTheme.colorScheme.onSurfaceVariant,
//                    textAlign = TextAlign.Center
//                )
            }
        }
    }
}