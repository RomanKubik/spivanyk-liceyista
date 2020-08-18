package com.roman.kubik.songer.settings.preferences

import com.roman.kubik.settings.domain.preference.Instrument
import com.roman.kubik.settings.domain.preference.PreferenceService
import com.roman.kubik.settings.domain.preference.Preferences
import com.roman.kubik.settings.domain.preference.UiMode
import javax.inject.Inject

class SharedPreferencesService @Inject constructor(): PreferenceService {
    override fun getPreferences(): Preferences {
        return Preferences(Instrument.GUITAR, UiMode.SYSTEM_DEFAULT, true)
    }

    override fun updatePreferences(preferences: Preferences) {
    }
}