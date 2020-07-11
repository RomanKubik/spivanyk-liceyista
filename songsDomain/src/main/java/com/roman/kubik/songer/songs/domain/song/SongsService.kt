package com.roman.kubik.songer.songs.domain.song

interface SongsService {

    suspend fun getAllSongs(): List<Song>
}