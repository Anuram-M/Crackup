package com.kumar.crackup.model

data class QuizQuestion(
    val id: Int,
    val question: String,
    val questionTamil: String = "",
    val options: List<String>, // expects exactly 4
    val optionsTamil: List<String> = emptyList(),
    val answer: Int, // 0..3
    val isPremium: Boolean = true,
)
