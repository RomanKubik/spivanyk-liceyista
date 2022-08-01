package com.roman.kubik.songer.analytics

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import com.roman.kubik.songer.analytics.core.BaseAnalyticsModule
import com.roman.kubik.songer.analytics.core.event.Event
import java.util.*
import java.util.regex.Pattern

class FirebaseAnalyticsModule : BaseAnalyticsModule() {

    companion object {
        /** Normalization pattern.  */
        private val NORM_PATTERN = Pattern.compile("[^a-zA-Z0-9]+")
        private val TAG = FirebaseAnalyticsModule::class.java.simpleName
    }

    private lateinit var analytics: FirebaseAnalytics

    override fun init(context: Context) {
        analytics = FirebaseAnalytics.getInstance(context)
    }

    override fun log(event: Event) {
        Log.d(TAG, "event name: ${event.name}\tevent attributes: ${event.attributes}")
        analytics.logEvent(normalize(event.name), getAttributesBundle(event))
    }

    private fun normalize(s: String): String {
        return NORM_PATTERN.matcher(s).replaceAll("_").toLowerCase(Locale.ENGLISH)
    }

    private fun getAttributesBundle(event: Event): Bundle {
        val bundle = Bundle()
        val attributes: Map<String, String> = event.attributes ?: emptyMap()
        for ((key, value) in attributes) {
            val normalizedKey: String = normalize(key)
            bundle.putString(normalizedKey, normalize(value))
        }
        return bundle
    }
}