package com.kumar.crackup.model

import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val id: String = "",
    val answer: Int = 0,
    val explanation: String = "",
    val explanation_ta: String = "",
    val group: String = "",
    val number: Int = 0,
    val options_en: List<String> = emptyList(),
    val options_ta: List<String> = emptyList(),
    val practiceType: String = "",
    val q_en: String = "",
    val q_ta: String = "",
    val subject: String = "",
    val subtopic: String = "",
//    val time: Timestamp = Timestamp(0,0),
    val unit: String = "",
    val year: String = "",
)
