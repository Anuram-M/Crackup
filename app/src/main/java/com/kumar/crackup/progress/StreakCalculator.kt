package com.kumar.crackup.progress

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

object StreakCalculator {
    private fun getFormatter(): SimpleDateFormat {
        return SimpleDateFormat("yyyy-MM-dd", Locale.US).apply {
            timeZone = TimeZone.getTimeZone("Asia/Kolkata")
        }
    }

    private fun getMonthYearFormatter(): SimpleDateFormat {
        return SimpleDateFormat("MMM,yyyy", Locale.US).apply {
            timeZone = TimeZone.getTimeZone("Asia/Kolkata")
        }
    }

    fun getTodayDate(): String {
        return getFormatter().format(Date())
    }

    fun getMonthYear(): String {
        return getMonthYearFormatter().format(Date())
    }

    fun calcualteStreak(
         currentStreakCount: Int,
         longestStreakCount: Int,
         lastSubmittedDate: String?
    ): Triple<Int, Int, String> {

        val currentDay = getTodayDate()

        if(lastSubmittedDate.isNullOrEmpty()) {
            return Triple(1, maxOf(1, longestStreakCount), currentDay)
        }

        if(lastSubmittedDate == currentDay) {
            return Triple(currentStreakCount, longestStreakCount, currentDay)
        }

        return try {
            val sdf = getFormatter()
            val lastDate = sdf.parse(lastSubmittedDate) ?: return Triple(1, maxOf(1, longestStreakCount), currentDay)
            val todayDate = sdf.parse(currentDay) ?: return Triple(1, maxOf(1, longestStreakCount), currentDay)

            val millisDifference = todayDate.time - lastDate.time
            val diffDays = TimeUnit.MILLISECONDS.toDays(millisDifference)

            when(diffDays) {
                1L -> {
                    val newStreak = currentStreakCount + 1
                    val newLongestStreak = maxOf(newStreak, longestStreakCount)
                    Triple(newStreak, newLongestStreak, currentDay)
                }
                else -> {
                    Triple(1, maxOf(1, maxOf(1, longestStreakCount)), currentDay)
                }
            }
        }catch (e: Exception) {
            Triple(1, maxOf(1, longestStreakCount), currentDay)
        }
    }
}