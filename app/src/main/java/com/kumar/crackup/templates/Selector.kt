package com.kumar.crackup.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kumar.crackup.ui.theme.bodyFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Selector(
    modifier: Modifier = Modifier.widthIn(min = 100.dp, max = 140.dp).padding(10.dp),
    options: List<String>,
    default: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }

    // 1. The container that wires up the TextField and the Dropdown anchor together
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier
//            .padding(horizontal = 10.dp, vertical = 5.dp),
    ) {
        // 2. The Anchor TextField
        Box(modifier = modifier
            .background(color = Color.Transparent, shape = RoundedCornerShape(10.dp))
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
            .clickable{
            expanded = !expanded
        },
            contentAlignment = Alignment.Center) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {

                BodyText(text = if(selectedOption.isNotEmpty()) selectedOption else default, modifier = Modifier.weight(1f))
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        }
//        OutlinedTextField(
//            value = selectedOption,
//            onValueChange = {}, // Keep read-only so typing doesn't mess up selections
//            readOnly = true,
//            label = { Text(text = label,style = TextStyle(
//                color = Color.Black,
//                fontFamily = bodyFont,
//            )) },
//            trailingIcon = {
//                // Standard drop-down arrow indicator animating based on open state
//                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
//            },
//            textStyle = TextStyle(
//                fontSize = bodyFontSmallSize,
//                color = Color.Black,
//                fontFamily = bodyFont,
//            ),
//            colors = OutlinedTextFieldDefaults.colors(
//                // Forces the crisp border outlines to be pure black
//
//                focusedBorderColor = Color.Gray,
//                unfocusedBorderColor = Color.LightGray,
//                focusedLabelColor = Color.Black,
//                unfocusedLabelColor = Color.Gray
//            ),
//            shape = RoundedCornerShape(20.dp), // Adjust roundness to preference
//            modifier = Modifier
//                .fillMaxWidth()
//                .menuAnchor(MenuAnchorType.PrimaryNotEditable, enabled = true) // Crucial anchor link in M3
//        )

        // 3. The actual popup menu list
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(Color.White)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option, style = TextStyle(
                        color = Color.Black,
                        fontFamily = bodyFont,
                        fontWeight = if(selectedOption == option) FontWeight.Bold else FontWeight.Normal
                    )) },
                    onClick = {
                        selectedOption = option
                        expanded = false
                        onOptionSelected(option) // Callback to pass the data back up
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}