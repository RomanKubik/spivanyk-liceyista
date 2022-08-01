package com.roman.kubik.songer.notification

data class Notification<T>(val id: Int,
                           val title: String,
                           val message: String,
                           val intent: NotificationIntent<T>)

data class NotificationIntent<T>(
        val screenName: Class<T>,
        val flags: Int
)