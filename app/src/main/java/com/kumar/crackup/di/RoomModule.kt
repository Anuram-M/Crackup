package com.kumar.crackup.di

import android.content.Context
import com.kumar.crackup.db.DailyChallengeDAO
import com.kumar.crackup.db.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideMyDatabase(@ApplicationContext context: Context) : MyDatabase {
        return MyDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideDailyChallengeDAO(myDatabase: MyDatabase): DailyChallengeDAO {
        return myDatabase.dailyChallengeDao()
    }
}