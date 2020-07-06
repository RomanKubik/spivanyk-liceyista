package com.roman.kubik.songer.domain.song

interface SongsService {

    suspend fun getAllSongs(): List<Song>
}