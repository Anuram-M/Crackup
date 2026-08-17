package com.kumar.crackup.util

import android.os.Build

fun getDeviceName(): String {
    val manufacturer = Build.MANUFACTURER // e.g., "samsung"
    val model = Build.MODEL               // e.g., "SM-G770F"

    return if (model.lowercase().startsWith(manufacturer.lowercase())) {
        capitalize(model)
    } else {
        "${capitalize(manufacturer)} $model"
    }
}

private fun capitalize(str: String): String {
    return str.split(" ")
        .joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }
}