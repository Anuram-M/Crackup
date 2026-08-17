package com.kumar.crackup.states

import androidx.compose.ui.graphics.Color

sealed class PasswordStrengthState(val label: String, val strengthColor: Color) {
    data object Empty: PasswordStrengthState(label = "", strengthColor = Color.Transparent)
    data object Poor: PasswordStrengthState(label = "Poor", strengthColor = Color.Black)
    data object Good: PasswordStrengthState(label = "Good", strengthColor = Color.Blue)
    data object Excellent: PasswordStrengthState(label = "Excellent", strengthColor = Color.Green)
}