package com.kumar.crackup.test

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp

data class AttemptModel(

    val setCategory: String = "",
    val attemptedCount: Int = 0,
    val correctCount: Int = 0,
    val accuracy: Float = 0f,
    val solvedQuestionsIds: List<String> = emptyList(),
    @ServerTimestamp val submittedAt: Timestamp? = null,
)
