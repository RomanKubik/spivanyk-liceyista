package com.roman.kubik.songer.di

import com.roman.kubik.settings.domain.preference.PreferenceService
import com.roman.kubik.songer.app.initializers.AnalyticsInitializer
import com.roman.kubik.songer.app.initializers.AppInitializer
import com.roman.kubik.songer.app.initializers.FcmInitializer
import com.roman.kubik.songer.app.initializers.ThemeInitializer
import com.roman.kubik.songer.data.core.SongSearcherProviderImpl
import com.roman.kubik.songer.mychords.song.MyChordsSongsService
import com.roman.kubik.songer.room.song.RoomSongService
import com.roman.kubik.songer.songs.domain.song.SongSearcherProvider
import com.roman.kubik.songer.songs.pisniorgua.PisniOrgUaSongsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getSongServiceProvider(
            roomSongService: RoomSongService,
            myChordsSongsService: MyChordsSongsService,
            pisniOrgUaSongsService: PisniOrgUaSongsService,
            preferenceService: PreferenceService
    ): SongSearcherProvider {
        val songServiceProvider = SongSearcherProviderImpl(roomSongService, myChordsSongsService, pisniOrgUaSongsService)

        songServiceProvider.updateSongConfig(preferenceService.getPreferences().selectedSongDataSource)

        return songServiceProvider
    }

    @Provides
    @Singleton
    fun getAppInitializers(analyticsInitializer: AnalyticsInitializer,
                           fcmInitializer: FcmInitializer,
                           themeInitializer: ThemeInitializer): List<AppInitializer> {
        return listOf(analyticsInitializer, fcmInitializer, themeInitializer)
    }
}