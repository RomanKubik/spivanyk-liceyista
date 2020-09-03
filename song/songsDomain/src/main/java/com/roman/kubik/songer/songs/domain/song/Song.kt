package com.roman.kubik.songer.songs.domain.song

import java.util.*

data class Song(val id: String, val title: String, val lyrics: String, val category: SongCategory, val isFavourite: Boolean, val source: String = "") {
    constructor(title: String, lyrics: String, category: SongCategory, source: String = "") : this(UUID.randomUUID().toString(), title, lyrics, category, false, source)
}