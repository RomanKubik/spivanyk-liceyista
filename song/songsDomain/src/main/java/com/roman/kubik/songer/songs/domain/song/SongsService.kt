package com.roman.kubik.songer.songs.domain.song

interface SongsService: SongsSearcher {

    suspend fun getAllSongs(): List<Song>

    suspend fun getAllSongs(songCategory: SongCategory): List<Song>

    suspend fun getLastPlayedSongs(): List<Song>

    suspend fun getFavouriteSongs(): List<Song>

    suspend fun createOrUpdateSong(song: Song)

    suspend fun addToLastPlayed(song: Song)

    suspend fun removeSong(song: Song)
}
