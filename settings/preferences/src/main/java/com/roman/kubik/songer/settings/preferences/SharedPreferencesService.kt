package com.roman.kubik.songer.settings.preferences

import android.content.Context
import android.content.SharedPreferences
import com.roman.kubik.settings.domain.preference.Instrument
import com.roman.kubik.settings.domain.preference.PreferenceService
import com.roman.kubik.settings.domain.preference.Preferences
import com.roman.kubik.settings.domain.preference.UiMode
import javax.inject.Inject

class SharedPreferencesService @Inject constructor(context: Context) : PreferenceService {

    companion object {
        const val SETTINGS_PREFERENCES = "preferences.settings"

        const val SETTINGS_INSTRUMENT = "settings.instrument"
        const val SETTINGS_UI_MODE = "settings.ui.mode"
        const val SETTINGS_SHOW_CHORDS = "settings.show.chords"
    }

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(SETTINGS_PREFERENCES, Context.MODE_PRIVATE)

    override fun getPreferences(): Preferences {
        return sharedPreferences.run {
            Preferences(
                    Instrument.valueOf(getString(SETTINGS_INSTRUMENT, Instrument.GUITAR.name)!!),
                    UiMode.valueOf(getString(SETTINGS_UI_MODE, UiMode.SYSTEM_DEFAULT.name)!!),
                    getBoolean(SETTINGS_SHOW_CHORDS, true))
        }
    }

    override fun updatePreferences(preferences: Preferences) {
        sharedPreferences.edit().apply {
            putString(SETTINGS_INSTRUMENT, preferences.selectedInstrument.name)
            putString(SETTINGS_UI_MODE, preferences.uiMode.name)
            putBoolean(SETTINGS_SHOW_CHORDS, preferences.showChords)
        }.apply()
    }
}