package com.roman.kubik.songer.di

import com.roman.kubik.songer.mychords.song.MyChordsSongsService
import com.roman.kubik.songer.room.song.RoomSongService
import com.roman.kubik.songer.songs.domain.song.SongsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.ElementsIntoSet
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    @ElementsIntoSet
    fun getSongServices(roomSongService: RoomSongService, myChordsSongsService: MyChordsSongsService): Set<SongsService> {
        return linkedSetOf(roomSongService, myChordsSongsService)
    }
}