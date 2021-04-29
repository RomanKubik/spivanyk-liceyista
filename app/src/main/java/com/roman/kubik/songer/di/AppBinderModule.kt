package com.roman.kubik.songer.di

import com.roman.kubik.ads.core.AdsModule
import com.roman.kubik.ads.google.GoogleAdsModule
import com.roman.kubik.provider.SongSercherConfigUpdater
import com.roman.kubik.settings.domain.database.DatabaseController
import com.roman.kubik.settings.domain.hint.HintService
import com.roman.kubik.settings.domain.preference.PreferenceService
import com.roman.kubik.settings.domain.theme.ThemeService
import com.roman.kubik.settings.hints.PreferenceHintService
import com.roman.kubik.songer.analytics.core.AnalyticsService
import com.roman.kubik.songer.analytics.core.DefaultAnalyticsService
import com.roman.kubik.songer.app.themes.ThemeServiceImpl
import com.roman.kubik.songer.core.data.StringProvider
import com.roman.kubik.songer.data.core.StringProviderImpl
import com.roman.kubik.songer.room.database.DatabaseManager
import com.roman.kubik.songer.room.database.DatabaseManagerImpl
import com.roman.kubik.songer.room.song.RoomSongService
import com.roman.kubik.songer.settings.preferences.SharedPreferencesService
import com.roman.kubik.songer.shaker.ShakeDetector
import com.roman.kubik.songer.shaker.ShakeDetectorImpl
import com.roman.kubik.songer.songs.domain.song.SongSearcherProvider
import com.roman.kubik.songer.songs.domain.song.SongsService
import com.roman.kubik.songer.songs.domain.song.SongsUpdateService
import com.roman.kubik.songer.songs.firebase.FirestoreSongsService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBinderModule {

    @Binds
    abstract fun bindStringProvider(stringProviderImpl: StringProviderImpl): StringProvider

    @Binds
    abstract fun bindDatabaseManager(databaseManagerImpl: DatabaseManagerImpl): DatabaseManager

    @Binds
    abstract fun bindDatabaseController(databaseManagerImpl: DatabaseManagerImpl): DatabaseController

    @Binds
    abstract fun bindPreferencesService(preferencesService: SharedPreferencesService): PreferenceService

    @Binds
    abstract fun bindThemeService(themeServiceImpl: ThemeServiceImpl): ThemeService

    @Binds
    abstract fun bindHintService(preferenceHintService: PreferenceHintService): HintService

    @Binds
    abstract fun bindSongServiceConfigUpdater(songSearcherProvider: SongSearcherProvider): SongSercherConfigUpdater

    @Binds
    abstract fun bindShakeDetector(shakeDetectorImpl: ShakeDetectorImpl): ShakeDetector

    @Singleton
    @Binds
    abstract fun bindAnalyticsService(defaultAnalyticsService: DefaultAnalyticsService): AnalyticsService

    @Singleton
    @Binds
    abstract fun bindAdsModule(googleAdsModule: GoogleAdsModule): AdsModule

    @Singleton
    @Binds
    abstract fun bindsSongsUpdaterService(firestoreSongsService: FirestoreSongsService): SongsUpdateService

    @Singleton
    @Binds
    abstract fun bindsSongsService(roomSongService: RoomSongService): SongsService

}