package com.kumar.crackup.model

import com.google.firebase.Timestamp

data class CA(
    val date: String,
    val currentAffairs: List<CAItems> = emptyList()
)

data class CAItems(
    val category: String,
    val categoryTamil: String,
    val affairs: List<CAContent> = emptyList()
)


data class CAContent(
    val title: String,
    val titleTamil: String,
    val content: List<CAFacts> = emptyList(),
    val readTime: String,
    val updatedAt: Timestamp
)

data class CAFacts(
    val heading: String,
    val headingTamil: String,
    val facts: List<String>,
    val factsTamil: List<String>
)