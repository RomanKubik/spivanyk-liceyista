package com.roman.kubik.songer.di

import android.content.Context
import com.roman.kubik.songer.room.database.AppDatabase
import com.roman.kubik.songer.room.database.DatabaseManagerImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Provides
    fun getContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun getAppDatabase(@ApplicationContext context: Context) = DatabaseManagerImpl.generateAppDatabase(context)

    @Provides
    @Reusable
    fun getSongDao(appDatabase: AppDatabase) = appDatabase.songDao()

    @Provides
    @Reusable
    fun getHistoryDao(appDatabase: AppDatabase) = appDatabase.historyDao()

    @Provides
    @Reusable
    fun getFavouriteDao(appDatabase: AppDatabase) = appDatabase.favouriteDao()

}