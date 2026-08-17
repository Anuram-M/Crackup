package com.kumar.crackup.templates

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.kumar.crackup.ui.theme.bodyFont
import com.kumar.crackup.ui.theme.bodyFontSmallSize

@Composable
fun OutlinedInputField(
    keyboardType: KeyboardType,
    label: String,
    placeHolder: String,
    isPassword: Boolean = false,
    modifier: Modifier = Modifier,
    textValue: String,
    checkStrength: Boolean = false,
    maxCharCount: Int = Int.MAX_VALUE,
    errorField: String? = null,
    onTextChanged: (String) -> Unit,
) {
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        value = textValue,
        onValueChange = {
            if(it.length <= maxCharCount) {
                onTextChanged(it)
            }
        },

        label = {
            Text(
                text = label,
                style = TextStyle(
                    color = Color.Black,
                    fontFamily = bodyFont,
                )
            )
        },
        visualTransformation = if(isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        textStyle = TextStyle(
            fontSize = bodyFontSmallSize,
            color = Color.Black,
            fontFamily = bodyFont,
        ),
        isError = errorField != null,
        supportingText = { errorField?.let { Text(it) } },
        placeholder = {
            Text(
                text = placeHolder,
                style = TextStyle(
                    color = Color.LightGray,
                    fontFamily = bodyFont,
                )
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Gray,
            unfocusedIndicatorColor = Color.LightGray,
            errorContainerColor = Color.White,
            errorIndicatorColor = Color.Red
        )
    )
}