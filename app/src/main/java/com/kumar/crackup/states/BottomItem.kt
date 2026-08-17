package com.kumar.crackup.states

import com.kumar.crackup.R

sealed class BottomItem(val route: String,val name: String, val icon: Int) {
    data object Home: BottomItem(route = "home", name = "Home", icon = R.drawable.home)
    data object Profile: BottomItem(route = "profile", name = "Profile", icon = R.drawable.profile)
    data object CurrentAffairs: BottomItem(route = "currentaffairs", name = "Current Affairs", icon = R.drawable.affairs)
    data object Progress: BottomItem(route = "progress", name = "Progress", icon = R.drawable.progress)
}