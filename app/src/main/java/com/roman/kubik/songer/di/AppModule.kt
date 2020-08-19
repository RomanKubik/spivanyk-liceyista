package com.roman.kubik.songer.di

import com.roman.kubik.songer.mychords.song.MyChordsSongsService
import com.roman.kubik.songer.room.song.RoomSongService
import com.roman.kubik.songer.songs.domain.song.SongsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Named

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Named("SongServices")
    fun getSongServices(roomSongService: RoomSongService, myChordsSongsService: MyChordsSongsService): List<SongsService> {
        return listOf(roomSongService, myChordsSongsService)
    }
}