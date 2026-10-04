package com.kumar.crackup.dailyChallenge

import com.kumar.crackup.model.NewQuestion
import com.kumar.crackup.progress.StreakCalculator
import kotlinx.serialization.Serializable
@Serializable
data class DailyChallengeModel(
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
    val questions: List<DailyChallengeModel> = emptyList(),
    val attempted: Int = 0,
    val showAnswer: Boolean = false,
    val currentQuestionIndex: Int = 0,
    val selectedAnswers: Map<Int, Int> = emptyMap(),
    val isPaused: Boolean = false,
    val elapsedTime: Long = 0L,
    val isCompleted: Boolean = false,
)


fun NewQuestion.toDailyChallenge() : DailyChallengeModel {
    return DailyChallengeModel(
        question = this.question,
        questionTamil = this.questionTamil,
        explanation = this.explanation,
        explanationTamil = this.explanationTamil,
        options = this.options,
        optionsTamil = this.optionsTamil,
        answer = this.answer
    )
}

fun DailyChallengeUiState.toDailyChallengeForRoom(): DailyChallenge {
    return DailyChallenge(
        answeredQuestions = this.attempted,
        currentQuestionIndex = this.currentQuestionIndex,
        date = StreakCalculator.getTodayDate(),
        questions = this.questions,
    )
}