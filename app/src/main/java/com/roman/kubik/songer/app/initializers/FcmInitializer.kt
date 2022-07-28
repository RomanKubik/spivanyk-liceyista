package com.roman.kubik.songer.app.initializers

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.roman.kubik.songer.R
import com.roman.kubik.songer.notification.NotificationManager.Companion.DEFAULT_CHANNEL_ID
import com.roman.kubik.songer.notification.NotificationManager.Companion.TOPIC_ALL
import javax.inject.Inject

class FcmInitializer @Inject constructor() : AppInitializer {
    override fun init(app: Application) {
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC_ALL)
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            Log.d("MyTag", it.result)
        }
        createNotificationChannel(app)
    }

    private fun createNotificationChannel(app: Application) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = app.getString(R.string.notification_channel_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(DEFAULT_CHANNEL_ID, name, importance)
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    app.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}