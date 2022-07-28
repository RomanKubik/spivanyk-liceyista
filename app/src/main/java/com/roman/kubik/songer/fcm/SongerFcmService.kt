package com.roman.kubik.songer.fcm

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
@AndroidEntryPoint
class SongerFcmService: FirebaseMessagingService() {

    @Inject
    lateinit var pushMessageHandler: PushMessageHandler

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        pushMessageHandler.handleMessage(message.data)
    }
}