package com.roman.kubik.songer.fcm

import android.content.Context
import javax.inject.Inject

class PushMessageHandlerImpl @Inject constructor(context: Context) : PushMessageHandler {
    override fun handleMessage(pushMessage: Map<String, String>) {
    }


    private companion object {
        private const val TYPE_KEY = "type"
        private const val TITLE_KEY = "title"
        private const val MESSAGE_KEY = "message"

        private const val TYPE_DB_UPDATE = "db_update"
        private const val TYPE_OTHER = "other"
    }
}