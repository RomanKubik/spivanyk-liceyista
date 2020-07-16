package com.roman.kubik.songer.songs.domain.song

interface SongsService {

    suspend fun getAllSongs(): List<Song>

    suspend fun getAllSongs(songCategory: SongCategory): List<Song>

    suspend fun getLastPlayedSongs(): List<Song>

    suspend fun getFavouriteSongs(): List<Song>

    suspend fun searchSongs(query: String): List<Song>

    suspend fun getSongById(songId: String): Song
}
