package com.kumar.crackup.model

import java.time.LocalDate

data class DailyBrief(
    val date: LocalDate,
    val importantNewsCount: Int,
    val summaryPoints: List<String>,
)