package com.roman.kubik.spivanyklicejista.general.android

import android.app.Application
import com.roman.kubik.spivanyklicejista.general.di.ApplicationComponent
import com.roman.kubik.spivanyklicejista.general.di.ApplicationModule
import com.roman.kubik.spivanyklicejista.general.di.DaggerApplicationComponent

/**
 * Spivanyk application
 * Created by kubik on 1/14/18.
 */

class SpivanykApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeDi()
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
