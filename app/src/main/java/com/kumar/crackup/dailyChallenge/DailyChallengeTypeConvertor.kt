package com.kumar.crackup.dailyChallenge

import androidx.room.TypeConverter
import com.kumar.crackup.util.QuestionAnswerState
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


    @TypeConverter
    fun fromQuestionAnswerMap(value: Map<Int, QuestionAnswerState>?): String {
        if (value.isNullOrEmpty()) return "{}"
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toQuestionAnswerMap(value: String?): Map<Int, QuestionAnswerState> {
        if (value.isNullOrBlank()) return emptyMap()
        return try {
            Json.decodeFromString(value)
        } catch (e: Exception) {
            emptyMap()
        }
    }

}