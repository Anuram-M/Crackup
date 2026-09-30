package com.kumar.crackup.db

import androidx.room.TypeConverter
import com.kumar.crackup.model.NewQuestion
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DailyChallengeTypeConvertor {

    @TypeConverter
    fun fromQuestions(questions: List<NewQuestion>) : String{
        return Json.encodeToString(questions)
    }

    @TypeConverter
    fun toQuestions(string: String) : List<NewQuestion> {
        return Json.decodeFromString(string)
    }
}