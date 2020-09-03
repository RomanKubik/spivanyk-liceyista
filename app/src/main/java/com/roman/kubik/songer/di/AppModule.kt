package com.roman.kubik.songer.di

import com.roman.kubik.songer.mychords.song.MyChordsSongsService
import com.roman.kubik.songer.room.song.RoomSongService
import com.roman.kubik.songer.songs.domain.song.SongsService
import com.roman.kubik.songer.songs.pisniorgua.PisniOrgUaSongsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getSongServices(roomSongService: RoomSongService, myChordsSongsService: MyChordsSongsService, pisniOrgUaSongsService: PisniOrgUaSongsService): Set<SongsService> {
        return linkedSetOf(roomSongService, pisniOrgUaSongsService, myChordsSongsService)
    }
}