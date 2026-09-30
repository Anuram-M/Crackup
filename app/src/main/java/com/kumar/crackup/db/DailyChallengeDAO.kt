package com.kumar.crackup.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DailyChallengeDAO {

    @Insert
    suspend fun insetQuestions(dailyChallenge: DailyChallenge)

    @Update
    suspend fun updateQuestions(dailyChallenge: DailyChallenge)

    @Query("DELETE from daily_challenge")
    suspend fun deleteAllQuestions()
}