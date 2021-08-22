package com.roman.kubik.songer.fcm

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.roman.kubik.songer.R
import com.roman.kubik.songer.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
@AndroidEntryPoint
class SongerFcmService: FirebaseMessagingService() {

    @Inject
    lateinit var pushMessageHandler: PushMessageHandler

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("MyTag", "onMessageReceived: ${message.data}")

        pushMessageHandler.handleMessage(message.data)

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val notification  = NotificationCompat.Builder(this, "0")
                .setSmallIcon(R.drawable.ic_bonfire)
                .setContentTitle(message.notification?.title)
                .setContentText(message.notification?.body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .build()

        NotificationManagerCompat.from(this).apply {
            notify(0, notification)
        }
    }
}