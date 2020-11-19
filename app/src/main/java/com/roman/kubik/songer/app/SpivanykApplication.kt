package com.roman.kubik.songer.app

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.roman.kubik.settings.domain.repository.SettingsRepository
import com.roman.kubik.settings.domain.theme.ThemeService
import com.roman.kubik.songer.BuildConfig
import com.roman.kubik.songer.analytics.core.AnalyticsService
import com.roman.kubik.songer.analytics.FirebaseAnalyticsModule
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class SpivanykApplication : Application() {

    @Inject
    lateinit var analyticsService: AnalyticsService
    @Inject
    lateinit var settingsRepository: SettingsRepository
    @Inject
    lateinit var themeService: ThemeService

    override fun onCreate() {
        super.onCreate()
        setupAnalytics()
        applyTheme()
    }

    private fun setupAnalytics() {
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)
        analyticsService.register(this, FirebaseAnalyticsModule())
    }

    private fun applyTheme() {
        GlobalScope.launch(Dispatchers.IO) {
            themeService.applyUiMode(settingsRepository.getPreferences().uiMode)
        }
    }
}
