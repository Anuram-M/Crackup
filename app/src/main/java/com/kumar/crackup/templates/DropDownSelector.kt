package com.kumar.crackup.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kumar.crackup.ui.theme.bodyFont
import com.kumar.crackup.ui.theme.bodyFontSmallSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownSelector(
    options: List<String>,
    label: String,
    errorField: String? = null,
    modifier: Modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
    resetError: () -> Unit,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }

    // 1. The container that wires up the TextField and the Dropdown anchor together
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier,
    ) {
        // 2. The Anchor TextField
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {}, // Keep read-only so typing doesn't mess up selections
            readOnly = true,
            label = { Text(text = label,style = TextStyle(
                color = Color.Black,
                fontFamily = bodyFont,
            )) },
            trailingIcon = {
                // Standard drop-down arrow indicator animating based on open state
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            isError = errorField != null,
            supportingText = { errorField?.let { Text(it) } },
            textStyle = TextStyle(
                fontSize = bodyFontSmallSize,
                color = Color.Black,
                fontFamily = bodyFont,
            ),
            colors = OutlinedTextFieldDefaults.colors(
                // Forces the crisp border outlines to be pure black
                errorTrailingIconColor = Color.Red,
                errorContainerColor = Color.White,
                errorBorderColor = Color.Red,
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.LightGray,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Gray
            ),
            shape = RoundedCornerShape(20.dp), // Adjust roundness to preference
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(MenuAnchorType.PrimaryNotEditable, enabled = true) // Crucial anchor link in M3
        )

        // 3. The actual popup menu list
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .heightIn(max = 250.dp)
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
                        if(errorField != null) resetError()
                        expanded = false
                        onOptionSelected(option) // Callback to pass the data back up
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}