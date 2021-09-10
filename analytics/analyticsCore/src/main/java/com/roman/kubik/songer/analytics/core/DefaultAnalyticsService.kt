package com.roman.kubik.songer.analytics.core

import android.app.Activity
import android.content.Context
import android.util.Log
import com.roman.kubik.songer.analytics.core.event.Event
import com.roman.kubik.songer.analytics.core.params.Property
import com.roman.kubik.songer.analytics.core.screen.Screen
import java.util.*
import java.util.concurrent.locks.ReentrantReadWriteLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultAnalyticsService @Inject constructor(): AnalyticsService {
    companion object {
        private val TAG: String = "DefaultAnalyticsService"
    }

    private val analytics: MutableSet<AnalyticsModule> = HashSet<AnalyticsModule>()
    private val logs: MutableSet<LogModule> = HashSet<LogModule>()

    private val analyticsLock = ReentrantReadWriteLock()
    private val logsLock = ReentrantReadWriteLock()

    override fun register(context: Context, module: Module) {
        try {
            module.init(context)
        } catch (e: Throwable) {
            // don't crash because of analytics
            if (BuildConfig.DEBUG) {
                Log.e(TAG, "Analytics module$module not initialized", e)
            }
            return
        }
        if (module is AnalyticsModule) {
            try {
                analyticsLock.writeLock().lock()
                analytics.add(module as AnalyticsModule)
            } finally {
                analyticsLock.writeLock().unlock()
            }
        }
        if (module is LogModule) {
            try {
                logsLock.writeLock().lock()
                logs.add(module as LogModule)
            } finally {
                logsLock.writeLock().unlock()
            }
        }
    }

    override fun unregister(module: Module) {
        if (module is AnalyticsModule) {
            try {
                analyticsLock.writeLock().lock()
                analytics.remove(module)
            } finally {
                analyticsLock.writeLock().unlock()
            }
        }
        if (module is LogModule) {
            try {
                logsLock.writeLock().lock()
                logs.remove(module)
            } finally {
                logsLock.writeLock().unlock()
            }
        }
    }

    override fun openSession(screen: Screen?) {
        try {
            analyticsLock.readLock().lock()
            for (module in analytics) {
                module.openSession(screen)
            }
        } finally {
            analyticsLock.readLock().unlock()
        }
    }

    override fun closeSession() {
        try {
            analyticsLock.readLock().lock()
            for (module in analytics) {
                module.closeSession()
            }
        } finally {
            analyticsLock.readLock().unlock()
        }
    }

    override fun upload() {
        try {
            analyticsLock.readLock().lock()
            for (module in analytics) {
                module.upload()
            }
        } finally {
            analyticsLock.readLock().unlock()
        }
    }

    override fun setUserProperty(property: Property) {
        try {
            analyticsLock.readLock().lock()
            for (module in analytics) {
                module.setUserProperty(property)
            }
        } finally {
            analyticsLock.readLock().unlock()
        }
    }

    override fun setUsername(username: String?) {
        try {
            analyticsLock.readLock().lock()
            for (module in analytics) {
                module.setUsername(username)
            }
        } finally {
            analyticsLock.readLock().unlock()
        }
    }

    override fun appStarted(activity: Activity) {
        try {
            analyticsLock.readLock().lock()
            for (module in analytics) {
                module.appStarted(activity)
            }
        } finally {
            analyticsLock.readLock().unlock()
        }
    }

    override fun log(event: Event) {
        if (!event.isValid) return
        try {
            analyticsLock.readLock().lock()
            for (module in analytics) {
                module.log(event)
            }
        } finally {
            analyticsLock.readLock().unlock()
        }
    }

    override fun log(screen: Screen) {
        try {
            analyticsLock.readLock().lock()
            for (module in analytics) {
                module.log(screen)
            }
        } finally {
            analyticsLock.readLock().unlock()
        }
    }

    fun logHandledException(msg: String?, t: Throwable) {
        try {
            logsLock.readLock().lock()
            for (module in logs) {
                module.logHandledException(msg, t)
            }
        } finally {
            logsLock.readLock().unlock()
        }
    }

    fun leaveBreadcrumb(name: String, value: String?) {
        try {
            logsLock.readLock().lock()
            for (module in logs) {
                module.leaveBreadcrumb(name, value)
            }
        } finally {
            logsLock.readLock().unlock()
        }
    }
}