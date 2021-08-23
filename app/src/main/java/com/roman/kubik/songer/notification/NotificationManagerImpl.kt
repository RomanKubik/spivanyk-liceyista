package com.roman.kubik.songer.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.roman.kubik.songer.R
import com.roman.kubik.songer.notification.NotificationManager.Companion.DEFAULT_CHANNEL_ID
import com.roman.kubik.songer.notification.NotificationManager.Companion.NOTIFICATION_ID_EXTRA
import javax.inject.Inject

class NotificationManagerImpl @Inject constructor(private val context: Context): NotificationManager {

    override fun <T> showMessage(notification: Notification<T>) {
        val intent = Intent(context, notification.intent.screenName).apply {
            flags = notification.intent.flags
            putExtra(NOTIFICATION_ID_EXTRA, notification.id)
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val n = NotificationCompat.Builder(context, DEFAULT_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_bonfire)
                .setContentTitle(notification.title)
                .setContentText(notification.message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .build()

        NotificationManagerCompat.from(context).apply {
            notify(notification.id, n)
        }
    }

    override fun hideMessage(notificationId: Int) {
        NotificationManagerCompat.from(context).cancel(notificationId)
    }
}