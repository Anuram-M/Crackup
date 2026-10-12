package com.kumar.crackup.model

data class TopicProgress(
    val name: String,
    val completedPercent: Float,   // 0f..1f — how much of the topic's content is done
    val accuracyPercent: Float,    // 0f..1f — of attempted questions, % answered correctly
    val questionsDone: Int,
    val questionsTotal: Int,
)

data class NewTopicProgress(
    val progressId: String = "",                    // e.g., "pyq" or "pyq_tnpsc_group4"
    val parentName: String = "pyq",                  // e.g., "Previous Year Questions"
    val path: String = "",                  // e.g., "pyq/tnpsc_group4"
//    val level: Int = 0,                     // 0 = Parent Topic, 1 = Direct Subtopic

    // UI Progress Metrics
    val completedPercent: Float = 0f,       // uniqueQuestionsSolved / questionsTotal
    val accuracyPercent: Float = 0f,        // totalCorrectAnswers / totalQuestionsAttempted
    val questionsDone: Int = 0,             // uniqueQuestionsSolved
    val questionsTotal: Int = 1000,         // Total question bank size for this entire branch

    // Cumulative Performance Counters
    val attemptsCount: Int = 0,
    val totalQuestionsAttempted: Int = 0,
    val totalCorrectAnswers: Int = 0
)