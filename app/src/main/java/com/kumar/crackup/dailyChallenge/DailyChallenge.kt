package com.kumar.crackup.dailyChallenge

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_challenge")
data class DailyChallenge(
    @PrimaryKey
    val id: Int = 0,
    val date: String,
    val currentQuestionIndex: Int,
    val answeredQuestions: Int,
    val questions: List<DailyChallengeModel>
)