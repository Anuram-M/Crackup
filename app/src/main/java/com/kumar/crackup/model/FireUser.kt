package com.kumar.crackup.model

data class FireUser(
    val fullName: String,
    val userEmail: String,
    val mobileNumber: String,
    val exam: String,
    val district: String,
    val password: String,
    val confirmPassword: String,
    val isSubscribed: Boolean,
    val pyq: Boolean,
    val generalTamil: Boolean,
    val aptitude: Boolean,
    val fullPackage: Boolean
)
