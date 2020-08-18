package com.roman.kubik.songer.settings.presentation.ui.settings

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.roman.kubik.settings.domain.preference.Instrument
import com.roman.kubik.settings.domain.preference.Preferences
import com.roman.kubik.settings.domain.preference.UiMode
import com.roman.kubik.settings.domain.repository.SettingsRepository
import com.roman.kubik.songer.core.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel @ViewModelInject constructor(private val settingsRepository: SettingsRepository) : BaseViewModel() {

    private val _preferences = MutableLiveData<Preferences>()
    val preferences: LiveData<Preferences> = _preferences

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _preferences.postValue(settingsRepository.getPreferences())
        }
    }

    fun showChords(show: Boolean) {
        preferences.value?.let {
            val prefs = Preferences(it.selectedInstrument, it.uiMode, show)
            updatePreferences(prefs)
        }
    }

    fun selectInstrument(instrument: Instrument) {
        preferences.value?.let {
            val prefs = Preferences(instrument, it.uiMode, it.showChords)
            updatePreferences(prefs)
        }
    }

    fun changeUiMode(uiMode: UiMode) {
        preferences.value?.let {
            val prefs = Preferences(it.selectedInstrument, uiMode, it.showChords)
            updatePreferences(prefs)
        }
    }

    private fun updatePreferences(preferences: Preferences) {
        viewModelScope.launch(Dispatchers.IO) {
            settingsRepository.updatePreferences(preferences)
            _preferences.postValue(preferences)
        }
    }
}