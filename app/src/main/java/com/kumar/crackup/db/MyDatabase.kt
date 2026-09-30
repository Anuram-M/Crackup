package com.kumar.crackup.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [DailyChallenge::class], version = 1, exportSchema = false)
@TypeConverters(DailyChallengeTypeConvertor::class)
abstract class MyDatabase : RoomDatabase() {

    abstract fun dailyChallengeDao(): DailyChallengeDAO

    companion object {
        @Volatile
        var database: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            return database ?: synchronized(this) {
                database ?: Room.databaseBuilder(
                    context,
                    MyDatabase::class.java,
                    "crackup_db"
                ).build().also {
                    database = it
                }
            }
        }
    }
}