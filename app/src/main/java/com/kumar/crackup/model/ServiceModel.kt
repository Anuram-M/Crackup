package com.kumar.crackup.model

data class ServiceModel(
    val title: String,
    val category: String,
    val includedFeatures: List<String>,
    val price: String,
    val eligibleFor: List<String> = emptyList()
)
