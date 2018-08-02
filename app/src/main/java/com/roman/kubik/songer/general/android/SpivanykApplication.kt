package com.roman.kubik.songer.general.android

import android.app.Application
import com.roman.kubik.songer.general.di.ApplicationComponent
import com.roman.kubik.songer.general.di.ApplicationModule
import com.roman.kubik.songer.general.di.DaggerApplicationComponent
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric



/**
 * Spivanyk application
 * Created by kubik on 1/14/18.
 */

class SpivanykApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeCrashlytics()
        initializeDi()
    }

    private fun initializeCrashlytics() {
        Fabric.with(this, Crashlytics())
    }

    private fun initializeDi() {
        SpivanykApplication.component = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(applicationContext))
                .build()
    }

    companion object {
        lateinit var component: ApplicationComponent
    }
}
