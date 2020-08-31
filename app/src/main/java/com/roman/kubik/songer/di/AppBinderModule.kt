package com.roman.kubik.songer.di

import com.roman.kubik.settings.domain.database.DatabaseController
import com.roman.kubik.settings.domain.preference.PreferenceService
import com.roman.kubik.songer.core.data.StringProvider
import com.roman.kubik.songer.data.core.StringProviderImpl
import com.roman.kubik.songer.room.database.DatabaseManager
import com.roman.kubik.songer.room.database.DatabaseManagerImpl
import com.roman.kubik.songer.settings.preferences.SharedPreferencesService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppBinderModule {

    @Binds
    abstract fun bindStringProvider(stringProviderImpl: StringProviderImpl): StringProvider

    @Binds
    abstract fun bindDatabaseManager(databaseManagerImpl: DatabaseManagerImpl): DatabaseManager

    @Binds
    abstract fun bindDatabaseController(databaseManagerImpl: DatabaseManagerImpl): DatabaseController

    @Binds
    abstract fun bindPreferencesService(preferencesService: SharedPreferencesService): PreferenceService

}