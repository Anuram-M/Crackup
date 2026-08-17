package com.kumar.crackup.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kumar.crackup.R
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.ui.theme.dialogCardColor
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.textBlack

@Composable
fun NoContentDialog(
    topicName: String,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        containerColor = dialogCardColor,
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                painter = painterResource(R.drawable.empty), // or Icons.Outlined.HourglassEmpty
                contentDescription = null,
                modifier = Modifier.size(50.dp),
                tint = textBlack,
            )
        },
        title = { BodyText(text = "No content yet", textSize = bodyFontLargeSize, fontWeight = FontWeight.Bold) },
        text = {
            BodyText(
                text =
                "\"$topicName\" doesn't have any questions added yet. Check back soon!",
                fontWeight = FontWeight.Medium,
            )
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                BodyText(text = "OK", textColor = lightBlue)
            }
        },
    )
}