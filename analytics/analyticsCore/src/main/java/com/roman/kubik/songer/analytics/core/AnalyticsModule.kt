package com.roman.kubik.songer.analytics.core

import android.app.Activity
import com.roman.kubik.songer.analytics.core.event.Event
import com.roman.kubik.songer.analytics.core.params.Property
import com.roman.kubik.songer.analytics.core.screen.Screen

interface AnalyticsModule : Module {
    fun appStarted(activity: Activity)
    fun log(event: Event)
    fun log(screen: Screen)
    fun setUserProperty(property: Property)
    fun setUsername(username: String?)
    fun openSession(screen: Screen?)
    fun closeSession()
    fun upload()
}