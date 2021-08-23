package com.roman.kubik.songer.fcm

interface PushMessageHandler {

    fun handleMessage(pushMessage: Map<String, String>)
}