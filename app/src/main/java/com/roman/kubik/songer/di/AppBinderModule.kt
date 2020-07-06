package com.roman.kubik.songer.di

import com.roman.kubik.songer.data.core.StringProvider
import com.roman.kubik.songer.data.core.StringProviderImpl
import com.roman.kubik.songer.data.local.database.DatabaseManager
import com.roman.kubik.songer.data.local.database.DatabaseManagerImpl
import com.roman.kubik.songer.data.local.song.RoomSongService
import com.roman.kubik.songer.domain.song.SongsService
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
    abstract fun bindRoomSongService(roomSongService: RoomSongService): SongsService
}