package com.roman.kubik.songer.songs.domain.repository

import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.domain.song.SongCategory
import com.roman.kubik.songer.songs.domain.song.SongCategory.*
import com.roman.kubik.songer.songs.domain.song.SongsService
import javax.inject.Inject
import kotlin.math.abs
import kotlin.random.Random

class SongRepository @Inject constructor(private val songsService: SongsService) {

    suspend fun getAllSongs(): List<Song> = songsService.getAllSongs()

    suspend fun getAllSongs(category: SongCategory): List<Song> {
        return when (category) {
            LAST_PLAYED -> songsService.getLastPlayedSongs()
            FAVOURITE -> songsService.getFavouriteSongs()
            else -> songsService.getAllSongs(category)
        }
    }

    suspend fun getSongById(songId: String): Song = songsService.getSongById(songId)

    suspend fun searchSong(query: String): List<Song> {
        return songsService.searchSongs(query)
    }

    suspend fun createOrUpdateSong(song: Song) {
        songsService.createOrUpdateSong(song)
    }

    suspend fun getRandomSong(): Song {
        val songs = songsService.getAllSongs()
        val randomIdx = abs(Random.nextInt()) % songs.size
        return songs[randomIdx]
    }

}