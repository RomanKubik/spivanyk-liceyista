package com.roman.kubik.songer.domain.repository

import com.roman.kubik.songer.domain.song.Song
import com.roman.kubik.songer.domain.song.SongsService
import javax.inject.Inject

class SongRepository @Inject constructor(private val songsService: SongsService) {

    suspend fun getAllSongs(): List<Song> = songsService.getAllSongs()

}