package com.kumar.crackup.dailyChallenge

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DailyChallengeTypeConvertor {

    @TypeConverter
    fun fromQuestions(questions: List<DailyChallengeModel>) : String{
        return Json.Default.encodeToString(questions)
    }

    @TypeConverter
    fun toQuestions(string: String) : List<DailyChallengeModel> {
        return Json.Default.decodeFromString(string)
    }
}