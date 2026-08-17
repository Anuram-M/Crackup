package com.kumar.crackup.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object PrefConstants {
    val IS_LOGGED_IN = "isLoggedIn"
    val IS_ONBOARD_SHOWN = "isOnboardShown"
    val IS_CURRENT = "isCurrent"
    const val SHOW_CONTACT = "showContact"
}

object PreferenceUtil {

    lateinit var prefs : SharedPreferences


    fun init(context: Context) {
        prefs = context.applicationContext.getSharedPreferences("myPref", Context.MODE_PRIVATE)
    }


    fun putString(key: String, value: String) {
        prefs.edit { putString(key, value) }
    }

    fun getString(key: String) : String? {
        return prefs.getString(key, "empty")
    }
    fun putBoolean(key: String, value: Boolean) {
        prefs.edit { putBoolean(key, value) }
    }

    fun getBoolean( key: String) : Boolean {
        return prefs.getBoolean(key, false)
    }
}