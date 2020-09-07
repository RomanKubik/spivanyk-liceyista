package com.roman.kubik.songer.settings.presentation.ui.settings

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.roman.kubik.provider.SongDataSource
import com.roman.kubik.settings.domain.database.DatabaseController
import com.roman.kubik.settings.domain.preference.Instrument
import com.roman.kubik.settings.domain.preference.Preferences
import com.roman.kubik.settings.domain.preference.UiMode
import com.roman.kubik.settings.domain.repository.SettingsRepository
import com.roman.kubik.songer.core.ui.base.BaseViewModel
import com.roman.kubik.songer.settings.presentation.navigation.SettingsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsViewModel @ViewModelInject constructor(
        private val settingsRepository: SettingsRepository,
        private val databaseController: DatabaseController,
        private val settingsNavigator: SettingsNavigator
) : BaseViewModel() {

    val allDataSources = linkedSetOf(SongDataSource.PISNI_ORG_UA, SongDataSource.MY_CHORDS_NET)

    private val _preferences = MutableLiveData<Preferences>()
    val preferences: LiveData<Preferences> = _preferences

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _preferences.postValue(settingsRepository.getPreferences())
        }
    }

    fun showChords(show: Boolean) {
        preferences.value?.let {
            val prefs = it.copy(showChords = show)
            updatePreferences(prefs)
        }
    }

    fun selectInstrument(instrument: Instrument) {
        preferences.value?.let {
            val prefs = it.copy(selectedInstrument = instrument)
            updatePreferences(prefs)
        }
    }

    fun changeUiMode(uiMode: UiMode) {
        preferences.value?.let {
            val prefs = it.copy(uiMode = uiMode)
            updatePreferences(prefs)
        }
    }

    private fun updatePreferences(preferences: Preferences) {
        viewModelScope.launch(Dispatchers.IO) {
            settingsRepository.updatePreferences(preferences)
            _preferences.postValue(preferences)
        }
    }

    fun factoryReset() {
        viewModelScope.launch(Dispatchers.IO) {
            databaseController.reset()
            withContext(Dispatchers.Main) {
                settingsNavigator.restart()
            }
        }
    }

    fun getSelectedDataSources(): BooleanArray {
        val result = BooleanArray(allDataSources.size)
        allDataSources.forEachIndexed { index, source ->
            result[index] = preferences.value?.selectedSongDataSource?.contains(source) ?: false
        }
        return result
    }

    fun selectDataSources(selectedDataSources: BooleanArray) {
        val result = linkedSetOf<SongDataSource>()
        allDataSources.forEachIndexed { index, source ->
            if (selectedDataSources[index]) {
                result.add(source)
            }
        }
        preferences.value?.let {
            val prefs = it.copy(selectedSongDataSource = result)
            updatePreferences(prefs)
        }
    }

}