package com.kumar.crackup.model

data class ExamItem(
    val title: String = "UPSC - Union Public Service Commission",
    val description: String = "India's premier constitutional body requiring officers for All-India and Central Civil Service",
    val bullets: List<ExamTypes> = emptyList()
)

data class ExamTypes(
    val heading: String,
    val exams: String,
)