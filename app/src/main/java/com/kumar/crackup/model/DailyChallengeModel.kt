package com.kumar.crackup.model

import kotlinx.serialization.Serializable
@Serializable
data class DailyChallengeModel(
    val questionId: Int = 0,
    val answer: Int = 0,
    val explanation: String = "",
    val explanationTamil: String = "",
    val options: List<String> = emptyList(),
    val optionsTamil: List<String> = emptyList(),
    val question: String = "",
    val questionTamil: String = "",
)


@Serializable
data class DailyChallengeUiState(
    val question: DailyChallengeModel,
    val showAnswer: Boolean = false,
    val selectedOptionIndex: Int? = null
)

@Serializable
data class DailyChallengeScreenUiState(
    val questions: List<DailyChallengeUiState> = emptyList(),
    val currentIndex: Int = 0
)