package com.roman.kubik.songer.app

import android.app.Application
import com.roman.kubik.songer.app.initializers.AppInitializer
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class SpivanykApplication : Application() {

    @Inject
    lateinit var appInitializer: AppInitializer

    override fun onCreate() {
        super.onCreate()
        appInitializer.init(this)
    }
}
