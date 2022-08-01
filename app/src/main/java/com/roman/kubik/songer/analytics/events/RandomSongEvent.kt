package com.roman.kubik.songer.analytics.events

import com.roman.kubik.songer.analytics.core.event.Event
import java.util.*

data class RandomSongEvent(val randomSource: Source): Event() {

    enum class Source {
        BOTTOM_MENU, SHAKE
    }

    override val name: String = "random_song"
    override val attributes: Map<String, String> = mapOf("source" to randomSource.name)
}