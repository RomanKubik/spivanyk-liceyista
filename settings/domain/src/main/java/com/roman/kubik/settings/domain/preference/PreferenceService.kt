package com.roman.kubik.settings.domain.preference

interface PreferenceService {

    fun getPreferences(): Preferences

    fun updatePreferences(preferences: Preferences)
}