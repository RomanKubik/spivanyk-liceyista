package com.roman.kubik.songer.app.initializers

import android.app.Application
import com.roman.kubik.settings.domain.repository.SettingsRepository
import com.roman.kubik.settings.domain.theme.ThemeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ThemeInitializer @Inject constructor(
        private val themeService: ThemeService,
        private val settingsRepository: SettingsRepository) : AppInitializer {

    override fun init(app: Application) {
        GlobalScope.launch(Dispatchers.IO) {
            themeService.applyUiMode(settingsRepository.getPreferences().uiMode)
        }
    }
}