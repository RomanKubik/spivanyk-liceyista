package com.roman.kubik.songer.data.local.di

import android.content.Context
import com.roman.kubik.songer.data.local.database.AppDatabase
import com.roman.kubik.songer.data.local.database.DatabaseManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun getAppDatabase(@ApplicationContext context: Context) = DatabaseManagerImpl.generateAppDatabase(context)

    @Provides
    fun getSongDao(appDatabase: AppDatabase) = appDatabase.songDao()
}