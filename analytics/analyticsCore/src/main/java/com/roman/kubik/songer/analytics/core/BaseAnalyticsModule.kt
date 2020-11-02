package com.roman.kubik.songer.analytics.core

import android.app.Activity
import com.roman.kubik.songer.analytics.core.event.Event
import com.roman.kubik.songer.analytics.core.params.Property
import com.roman.kubik.songer.analytics.core.screen.Screen

abstract class BaseAnalyticsModule : AnalyticsModule {
    override fun appStarted(activity: Activity) {}
    override fun log(event: Event) {}
    override fun log(screen: Screen) {}
    override fun setUserProperty(property: Property) {}
    override fun setUsername(username: String?) {}
    override fun openSession(screen: Screen?) {}
    override fun closeSession() {}
    override fun upload() {}
}