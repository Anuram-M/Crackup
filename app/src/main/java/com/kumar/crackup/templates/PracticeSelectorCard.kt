package com.kumar.crackup.templates

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.kumar.crackup.test.PracticeItem
import com.kumar.crackup.ui.theme.bodyFontTinySize
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.screenBackground

@Composable
fun PracticeSelectorCard(item: PracticeItem, isSelected: Boolean, onPress: () -> Unit) {

    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp).selectable(
            selected = isSelected,
            onClick = { onPress() },
            role = Role.RadioButton
        ).border(width = 1.dp, color = if (isSelected) Color.Black else  Color.Gray, shape = RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(containerColor = screenBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = if(isSelected) 4.dp else 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column() {
                HeaderText(text = item.name, modifier = Modifier)
                BodyText(text = item.subText, modifier = Modifier, textSize = bodyFontTinySize)
            }

            Spacer(Modifier.width(8.dp))
            RadioButton(
                selected = isSelected,
                onClick = null, // Handled by Row
                colors = RadioButtonDefaults.colors(selectedColor = lightBlue, unselectedColor = Color.Gray)
            )
        }
    }
}