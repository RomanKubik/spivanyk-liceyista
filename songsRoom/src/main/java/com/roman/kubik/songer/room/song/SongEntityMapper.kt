package com.roman.kubik.songer.room.song

import com.roman.kubik.songer.songs.domain.song.Song

fun SongEntity.toSong(): Song = Song(id, title, lyrics)