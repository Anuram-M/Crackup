package com.kumar.crackup.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kumar.crackup.model.NewQuestion

@Entity(tableName = "daily_challenge")
data class DailyChallenge(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val totalQuestions: Int,
    val answeredQuestions: Int,
    val questions: List<NewQuestion>
)
