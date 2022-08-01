package com.roman.kubik.songer.analytics.core

interface LogModule : Module {
    fun logHandledException(msg: String?, t: Throwable)
    fun leaveBreadcrumb(name: String, value: String?)
}