package com.roman.kubik.songer.songs.domain.song

interface SongsSearcher {

    suspend fun searchSongs(query: String): List<Song>

    suspend fun getSongById(songId: String): Song

}