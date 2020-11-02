package com.roman.kubik.songer.analytics.core

abstract class BaseLogModule : LogModule {
    override fun logHandledException(msg: String?, t: Throwable) {}
    override fun leaveBreadcrumb(name: String, value: String?) {}
}