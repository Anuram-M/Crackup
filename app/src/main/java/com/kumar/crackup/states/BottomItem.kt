package com.kumar.crackup.states

import com.kumar.crackup.R
import com.kumar.crackup.home.HomeNav

sealed class BottomItem(val route: Any, val routeKey: String, val name: String, val icon: Int) {
    data object Home: BottomItem(
        route = HomeNav(),
        routeKey = "home",
        name = "Home",
        icon = R.drawable.home)
    data object Profile: BottomItem(
        route = "profile",
        routeKey = "profile",
        name = "Profile",
        icon = R.drawable.profile)
    data object CurrentAffairs: BottomItem(
        route = "currentaffairs",
        routeKey = "currentaffairs",
        name = "Affairs",
        icon = R.drawable.affairs)
    data object Progress: BottomItem(
        route = "progress",
        routeKey = "progress",
        name = "Progress",
        icon = R.drawable.progress)
}