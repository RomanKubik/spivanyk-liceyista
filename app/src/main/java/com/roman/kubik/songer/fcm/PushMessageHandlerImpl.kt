package com.roman.kubik.songer.fcm

import android.content.Context
import android.content.Intent
import com.roman.kubik.songer.R
import com.roman.kubik.songer.notification.Notification
import com.roman.kubik.songer.notification.NotificationIntent
import com.roman.kubik.songer.notification.NotificationManager
import com.roman.kubik.songer.songs.domain.repository.SongRepository
import com.roman.kubik.songer.ui.main.MainActivity
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
class PushMessageHandlerImpl @Inject constructor(
        private val context: Context,
        private val notificationManager: NotificationManager,
        private val songRepository: SongRepository
) : PushMessageHandler {

    override fun handleMessage(pushMessage: Map<String, String>) {
        when (pushMessage[TYPE_KEY]) {
            TYPE_FORCE_UPDATE -> {
                handleDbUpdateType(pushMessage)
            }
            else -> {
                handleOtherType(pushMessage)
            }
        }
    }

    private fun handleDbUpdateType(pushMessage: Map<String, String>) {
        GlobalScope.launch {
            songRepository.fetchNewSongs(true)
        }
        val notification = Notification(
                id = ID_FORCE_UPDATE,
                title = pushMessage[TITLE_KEY] ?: context.getString(R.string.app_name),
                message = pushMessage[MESSAGE_KEY] ?: context.getString(R.string.notification_generic_message),
                intent = NotificationIntent(
                        screenName = MainActivity::class.java,
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                )
        )
        notificationManager.showMessage(notification)
    }

    private fun handleOtherType(pushMessage: Map<String, String>) {
        val notification = Notification(
                id = ID_OTHER,
                title = pushMessage[TITLE_KEY] ?: context.getString(R.string.app_name),
                message = pushMessage[MESSAGE_KEY] ?: context.getString(R.string.notification_generic_message),
                intent = NotificationIntent(
                        screenName = MainActivity::class.java,
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                )
        )
        notificationManager.showMessage(notification)
    }

    private companion object {
        private const val TYPE_KEY = "type"
        private const val TITLE_KEY = "title"
        private const val MESSAGE_KEY = "message"

        private const val TYPE_FORCE_UPDATE = "force_update"

        private const val ID_OTHER = 0
        private const val ID_FORCE_UPDATE = 1
    }
}