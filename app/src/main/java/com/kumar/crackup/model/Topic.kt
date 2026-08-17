package com.kumar.crackup.model

enum class TopicType { EXAM_YEAR, SUB_TOPIC, UNIT_SUB_TOPIC }

data class Topic(
    val id: String = "",
    val name: String = "",
    val nameTamil: String = "",
    val type: TopicType = TopicType.SUB_TOPIC,
    val topicQuery: String = "",
    val order: Int = 0,
)
data class TamilUnit(
    val id: String = "1",
    val topicId: String = "4UtHJt5Xc7yOvrn9Wr5Z",
    val name: String = "",
    val nameTamil: String = "",
    val unitName: String = "",
    val unitTopics: List<String> = emptyList(),
    val unitTopicsEnglish: List<String> = emptyList(),
    val order: Int = 0,
)

data class SubTopic(
    val id: String = "1",
    val topicId: String = "1",
    val name: String = "Subdivision",
    val nameTamil: String = "",
    val subtopicQuery: String = "",
    val order: Int = 0,
)

data class Exam(
    val id: String = "1",
    val topicId: String = "0b3461ba-41df-4fdd-b065-378997a539de",
    val name: String = "Group |",
    val availableYears: List<Int> = listOf(2017, 2019, 2021, 2022, 2024, 2025),
    val order: Int = 0,
)

