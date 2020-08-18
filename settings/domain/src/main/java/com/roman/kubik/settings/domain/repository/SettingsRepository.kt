package com.roman.kubik.settings.domain.repository

import com.roman.kubik.settings.domain.preference.PreferenceService
import com.roman.kubik.settings.domain.preference.Preferences
import javax.inject.Inject

class SettingsRepository @Inject constructor(private val preferenceService: PreferenceService) {

    suspend fun getPreferences(): Preferences {
        return preferenceService.getPreferences()
    }

    suspend fun updatePreferences(preferences: Preferences) {
        preferenceService.updatePreferences(preferences)
    }

}