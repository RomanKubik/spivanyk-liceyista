package com.roman.kubik.songer.songs.domain.repository

import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.domain.song.SongsService
import javax.inject.Inject

class SongRepository @Inject constructor(private val songsService: SongsService) {

    suspend fun getAllSongs(): List<Song> = songsService.getAllSongs()

    suspend fun getSongById(songId: String): Song = songsService.getSongById(songId)
}