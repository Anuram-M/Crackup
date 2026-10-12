package com.kumar.crackup.model

data class WeekStreak(
    val label: String,
    val daysCompleted: Int,
    val totalDays: Int = 7
)