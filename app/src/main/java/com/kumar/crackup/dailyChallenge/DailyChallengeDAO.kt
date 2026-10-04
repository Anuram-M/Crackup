package com.kumar.crackup.dailyChallenge

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyChallengeDAO {

    @Insert
    suspend fun insetQuestions(dailyChallenge: DailyChallenge)

    @Query("SELECT * FROM DAILY_CHALLENGE")
    fun getDailyChallenge(): Flow<DailyChallenge?>

    @Upsert
    suspend fun updateQuestions(dailyChallenge: DailyChallenge)

    @Query("DELETE from daily_challenge")
    suspend fun deleteAllQuestions()
}