package com.kumar.crackup.home

import kotlinx.serialization.Serializable

@Serializable
data class HomeNav(
    val route: String = "home",
    val section: String = "header"
)


@Serializable
data object OnBoarding


@Serializable
data object Login