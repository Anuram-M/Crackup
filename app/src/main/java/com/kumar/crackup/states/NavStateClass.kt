package com.kumar.crackup.states

sealed class NavStateClass {
    object ShowSplash : NavStateClass()
    object ShowOnboarding : NavStateClass()
    object ShowCurrent: NavStateClass()
}