package com.kumar.crackup.model

data class TopicProgress(
    val name: String,
    val completedPercent: Float,   // 0f..1f — how much of the topic's content is done
    val accuracyPercent: Float,    // 0f..1f — of attempted questions, % answered correctly
    val questionsDone: Int,
    val questionsTotal: Int,
)