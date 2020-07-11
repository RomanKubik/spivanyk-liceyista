package com.roman.kubik.songer.songs.domain.song

interface SongsService {

    suspend fun getAllSongs(): List<Song>

    suspend fun getSongById(songId: String): Song
}