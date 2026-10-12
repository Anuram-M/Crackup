package com.kumar.crackup

data class UserModel(
    val name: String,
    val email: String,
    val mobile: String,
    val district: String,
    val exam: String,
    val isSubscribed: Boolean?,
    val pyq: Boolean?,
    val generalTamil: Boolean?,
    val generalStudies: Boolean?,
    val aptitude: Boolean?,
    val fullPackage: Boolean?,
    val currentStreak: Int?,
    val longestStreak: Int?,
    val lastStreakDate: String?,
    val activeDates: List<String>
)
