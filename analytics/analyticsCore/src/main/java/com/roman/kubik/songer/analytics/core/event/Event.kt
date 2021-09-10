package com.roman.kubik.songer.analytics.core.event

abstract class Event {

    abstract val name: String

    abstract val attributes: Map<String, String>?

    val isValid: Boolean
        get() = true
}