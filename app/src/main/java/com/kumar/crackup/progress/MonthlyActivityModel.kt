package com.kumar.crackup.progress

data class MonthlyActivityModel(
    val month: String = "",
    val activeDates: List<String> = emptyList(),
    val totalTests: Int = 0
)
