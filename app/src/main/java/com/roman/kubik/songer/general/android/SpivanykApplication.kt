package com.roman.kubik.songer.general.android

import androidx.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import com.roman.kubik.songer.BuildConfig
import com.roman.kubik.songer.general.di.ApplicationComponent
import com.roman.kubik.songer.general.di.ApplicationModule
import com.roman.kubik.songer.general.di.DaggerApplicationComponent
import io.fabric.sdk.android.Fabric


/**
 * Spivanyk application
 * Created by kubik on 1/14/18.
 */

class SpivanykApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initializeCrashlytics()
        initializeDi()
    }

    private fun initializeCrashlytics() {
        if (!BuildConfig.DEBUG)
            Fabric.with(this, Crashlytics())
    }

    private fun initializeDi() {
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(applicationContext))
                .build()
    }

    companion object {
        lateinit var component: ApplicationComponent
    }
}
