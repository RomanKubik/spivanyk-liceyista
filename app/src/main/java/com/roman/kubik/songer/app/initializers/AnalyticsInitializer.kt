package com.roman.kubik.songer.app.initializers

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.roman.kubik.songer.BuildConfig
import com.roman.kubik.songer.analytics.FirebaseAnalyticsModule
import com.roman.kubik.songer.analytics.core.AnalyticsService
import javax.inject.Inject

class AnalyticsInitializer @Inject constructor(private val analyticsService: AnalyticsService)
    : AppInitializer {
    override fun init(app: Application) {
        if (BuildConfig.DEBUG) return
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
        analyticsService.register(app, FirebaseAnalyticsModule())
    }
}