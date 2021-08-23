package com.roman.kubik.songer.app.initializers

import android.app.Application
import javax.inject.Inject

class ComposedAppInitializer @Inject constructor(private val initializers: List<@JvmSuppressWildcards AppInitializer>)
    : AppInitializer {

    override fun init(app: Application) {
        for (initializer in initializers) {
            initializer.init(app)
        }
    }
}