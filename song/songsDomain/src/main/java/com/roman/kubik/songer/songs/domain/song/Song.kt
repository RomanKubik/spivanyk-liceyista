package com.roman.kubik.songer.songs.domain.song

import java.util.*

data class Song(val id: String, val title: String, val lyrics: String, val category: SongCategory, val isFavourite: Boolean) {
    constructor(title: String, lyrics: String, category: SongCategory) : this(UUID.randomUUID().toString(), title, lyrics, category, false)
}