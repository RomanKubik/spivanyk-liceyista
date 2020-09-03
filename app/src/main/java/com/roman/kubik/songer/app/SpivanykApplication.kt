package com.roman.kubik.songer.app

import android.app.Application
import com.roman.kubik.settings.domain.repository.SettingsRepository
import com.roman.kubik.settings.domain.theme.ThemeService
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class SpivanykApplication : Application() {

    @Inject
    lateinit var settingsRepository: SettingsRepository

    @Inject
    lateinit var themeService: ThemeService

    override fun onCreate() {
        super.onCreate()
        GlobalScope.launch(Dispatchers.IO) {
            themeService.applyUiMode(settingsRepository.getPreferences().uiMode)
        }
    }
}
