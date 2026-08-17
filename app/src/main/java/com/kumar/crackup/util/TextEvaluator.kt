package com.kumar.crackup.util

import com.kumar.crackup.model.NewQuestion

// ============================================================
// DATA MODELS
// ============================================================



/**
 * Per-question answer state. `selectedOptionIndex == null` means the
 * question hasn't been touched at all — this is what creates "gaps".
 * `isMarkedFinal` is purely a confidence flag the user can set; it does
 * NOT affect whether the answer counts during evaluation. Any selected
 * answer counts, marked or not.
 */
data class QuestionAnswerState(
    val selectedOptionIndex: Int? = null,
    val isMarkedForReview: Boolean = false
)

/**
 * Result of scoring the whole quiz. `totalQuestions` is always the full
 * list size — score and unanswered counts are always relative to the
 * entire set, not just the questions the user happened to attempt.
 */
data class QuizResult(
    val totalQuestions: Int,
    val attempted: Int,
    val correct: Int,
    val incorrect: Int,
    val unanswered: Int,
    val accuracyAmongAttempted: Float // 0..100, based only on attempted questions
) {
    /** e.g. "18/24" — score expressed against the full question count. */
    val scoreLabel: String get() = "$correct/$totalQuestions"
}

// ============================================================
// EVALUATOR — pure, no Compose/Android imports, unit-testable
// ============================================================

object TestEvaluator {

    /**
     * Scores the quiz against the FULL question list. Any question with
     * no selection (a gap) is simply unanswered — it's never treated as
     * wrong for accuracy purposes, but it does reduce the score out of
     * the total, since totalQuestions never shrinks to "questions attempted".
     */
    fun evaluate(
        questions: List<NewQuestion>,
        answers: Map<Int, QuestionAnswerState> // keyed by question index, not id
    ): QuizResult {
        val total = questions.size
        var correct = 0
        var attempted = 0

        questions.forEachIndexed { index, question ->
            val selected = answers[index]?.selectedOptionIndex
            if (selected != null) {
                attempted++
                if (selected == question.answer) correct++
            }
        }

        val incorrect = attempted - correct
        val unanswered = total - attempted
        val accuracy = if (attempted > 0) (correct.toFloat() / attempted) * 100f else 0f

        return QuizResult(
            totalQuestions = total,
            attempted = attempted,
            correct = correct,
            incorrect = incorrect,
            unanswered = unanswered,
            accuracyAmongAttempted = accuracy
        )
    }
}
