package com.roman.kubik.songer.settings.preferences

import android.content.Context
import android.content.SharedPreferences
import com.roman.kubik.provider.SongDataSource
import com.roman.kubik.settings.domain.preference.*
import javax.inject.Inject

class SharedPreferencesService @Inject constructor(context: Context) : PreferenceService {

    companion object {
        const val SETTINGS_PREFERENCES = "preferences.settings"

        const val SETTINGS_INSTRUMENT = "settings.instrument"
        const val SETTINGS_UI_MODE = "settings.ui.mode"
        const val SETTINGS_SHOW_CHORDS = "settings.show.chords"
        const val SETTINGS_REMOTE_DATA_SET = "settings.remote.data.set"
        const val SETTINGS_SHOW_ADS = "settings.show.ads"
    }

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(SETTINGS_PREFERENCES, Context.MODE_PRIVATE)

    override fun getPreferences(): Preferences {
        return sharedPreferences.run {
            Preferences(
                    Instrument.valueOf(getString(SETTINGS_INSTRUMENT, Instrument.GUITAR.name)!!),
                    UiMode.valueOf(getString(SETTINGS_UI_MODE, UiMode.SYSTEM_DEFAULT.name)!!),
                    getBoolean(SETTINGS_SHOW_CHORDS, true),
                    getDataSources(this),
                    getBoolean(SETTINGS_SHOW_ADS, true)
            )
        }
    }

    override fun updatePreferences(preferences: Preferences) {
        sharedPreferences.edit().apply {
            putString(SETTINGS_INSTRUMENT, preferences.selectedInstrument.name)
            putString(SETTINGS_UI_MODE, preferences.uiMode.name)
            putBoolean(SETTINGS_SHOW_CHORDS, preferences.showChords)
            putDataSources(this, preferences.selectedSongDataSource)
            putBoolean(SETTINGS_SHOW_ADS, preferences.showAds)
        }.apply()
    }

    private fun getDataSources(sharedPreferences: SharedPreferences): Set<SongDataSource> {
        val defaultSources = linkedSetOf(SongDataSource.PISNI_ORG_UA, SongDataSource.MY_CHORDS_NET)
        val sources = sharedPreferences.getStringSet(SETTINGS_REMOTE_DATA_SET, null)
                ?.map { SongDataSource.valueOf(it) }
                ?.toMutableSet()
        return sources ?: defaultSources
    }

    private fun putDataSources(sharedPreferences: SharedPreferences.Editor, dataSources: Set<SongDataSource>) {
        sharedPreferences.putStringSet(SETTINGS_REMOTE_DATA_SET, dataSources.map { it.name }.toSet())
    }
}