package com.roman.kubik.settings.domain.repository

import com.roman.kubik.provider.SongSercherConfigUpdater
import com.roman.kubik.settings.domain.preference.PreferenceService
import com.roman.kubik.settings.domain.preference.Preferences
import com.roman.kubik.settings.domain.theme.ThemeService
import javax.inject.Inject

class SettingsRepository @Inject constructor(private val preferenceService: PreferenceService,
                                             private val themeService: ThemeService,
                                             private val songSercherProvider: SongSercherConfigUpdater) {

    suspend fun getPreferences(): Preferences {
        return preferenceService.getPreferences()
    }

    suspend fun updatePreferences(preferences: Preferences) {
        preferenceService.getPreferences().let {
            if (it.uiMode != preferences.uiMode) {
                themeService.applyUiMode(preferences.uiMode)
            }
            if (it.selectedSongDataSource != preferences.selectedSongDataSource) {
                songSercherProvider.updateSongConfig(preferences.selectedSongDataSource)
            }
        }

        preferenceService.updatePreferences(preferences)
    }

}