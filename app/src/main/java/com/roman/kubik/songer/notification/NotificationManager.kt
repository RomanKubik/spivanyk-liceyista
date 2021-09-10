package com.roman.kubik.songer.notification

interface NotificationManager {

    fun <T> showMessage(notification: Notification<T>)

    fun hideMessage(notificationId: Int)

    companion object {
        const val TOPIC_ALL = "all"

        const val DEFAULT_CHANNEL_ID = "default"

        const val NOTIFICATION_ID_EXTRA = "notification_id"
    }
}