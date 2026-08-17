package com.kumar.crackup.util

import androidx.compose.ui.graphics.Color
import com.kumar.crackup.states.PasswordStrengthState
import com.kumar.crackup.ui.theme.cOrange
import com.kumar.crackup.ui.theme.cYellow
import com.kumar.crackup.ui.theme.forestGreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object PasswordStrength {
private val strength = MutableStateFlow<Color>(Color.Transparent)
    val characterStrength : StateFlow<Color> = strength.asStateFlow()

    val allCase = "^[A-Z]+[a-z]+[0-9]+[^A-Za-z0-9]+\$".toRegex()

    val onlyUpperCase = "^[A-Z]+[a-z]+[^A-Za-z0-9]+\$".toRegex()
    val onlyLowerCase = "^[a-z]+\$".toRegex()
    val onlyNumCase = "^[0-9]+\$".toRegex()
    val onlySplCase = "^[^A-Za-z0-9]+\$".toRegex()

    val withoutNumCase = "^[A-Z]+[a-z]+[^A-Za-z0-9]+\$".toRegex()
    val withoutLowerCase = "^[A-Z]+[0-9]+[^A-Za-z0-9]+\$".toRegex()
    val withoutUpperCase = "^[a-z]+[0-9]+[^A-Za-z0-9]+\$".toRegex()
    val withoutSplCase = "^[A-Z]+[a-z]+[0-9]+\$".toRegex()

    fun checkCharacterStrength(password: String) {

        if(password.isEmpty()) {
            strength.value = Color.Transparent
            return
        }
        if(password.isNotEmpty() && password.length < 8) {
            strength.value = Color.Red
            return
        }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasDigits = password.any { it.isDigit() }
        val hasSpecial = password.any { !it.isLetterOrDigit() }


        val conditionsMet = listOf(hasUpperCase, hasLowerCase, hasDigits, hasSpecial).count { it }

        strength.value = when {
            conditionsMet == 4 && password.length >= 10 -> forestGreen
            conditionsMet >= 3 -> cYellow
            else -> cOrange
        }

    }
}