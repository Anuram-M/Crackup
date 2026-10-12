package com.kumar.crackup.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.kumar.crackup.ui.theme.bodyFontTinySize
import com.kumar.crackup.ui.theme.darkBlue
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.screenBackground

@Composable
fun LanguageSwitch(
    isEnglish: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Switch(
        checked = isEnglish,
        onCheckedChange = onToggle,
        modifier = modifier,
        colors = SwitchDefaults.colors(
            checkedTrackColor = darkBlue,
            checkedThumbColor = lightBlue,
            uncheckedThumbColor = lightBlue
        ),
        thumbContent = {
            Box (contentAlignment = Alignment.Center) {
                Text(
                    text = if (!isEnglish) "அ" else "A",
                    fontSize = bodyFontTinySize,
                    lineHeight = bodyFontTinySize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = screenBackground,
//                color = if (isTamilSelected)
//                    MaterialTheme.colorScheme.primary
//                else
//                    MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(SwitchDefaults.IconSize)
                )
            }

        }
    )
}