package com.roman.kubik.songer.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.roman.kubik.songer.R
import com.roman.kubik.songer.songs.domain.repository.SongRepository
import com.roman.kubik.songer.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SongerFcmService: FirebaseMessagingService() {

    @Inject
    lateinit var songRepository: SongRepository

    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        Log.d("MyTag", "newToken: $newToken")
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        Log.d("MyTag", "onMessageReceived: ${p0.data}")

        createNotificationChannel()
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val notification  = NotificationCompat.Builder(this, "0")
                .setSmallIcon(R.drawable.ic_bonfire)
                .setContentTitle("Horray")
                .setContentText("Check out thi new song for your perfect evening")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .build()

        NotificationManagerCompat.from(this).apply {
            notify(0, notification)
        }

//        GlobalScope.launch(Dispatchers.IO) {
//            songRepository.fetchNewSongs(true)
//        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Channel name"
            val descriptionText = "Channel description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("0", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}