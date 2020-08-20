package com.roman.kubik.songer.songs.domain.repository

import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.domain.song.SongCategory
import com.roman.kubik.songer.songs.domain.song.SongCategory.*
import com.roman.kubik.songer.songs.domain.song.SongsService
import javax.inject.Inject
import kotlin.math.abs
import kotlin.random.Random

class SongRepository @Inject constructor(private val songsServices: Set<@JvmSuppressWildcards SongsService>) {

    suspend fun getAllSongs(): List<Song> {
        val result = mutableListOf<Song>()
        for (songsService in songsServices) {
            result.addAll(songsService.getAllSongs())
        }
        return result
    }

    suspend fun getAllSongs(category: SongCategory): List<Song> {
        val result = mutableListOf<Song>()
        for (songsService in songsServices) {
            val r = when (category) {
                LAST_PLAYED -> songsService.getLastPlayedSongs()
                FAVOURITE -> songsService.getFavouriteSongs()
                else -> songsService.getAllSongs(category)
            }
            result.addAll(r)
        }
        return result
    }

    suspend fun getSongById(songId: String): Song {
        for (songsService in songsServices) {
            try {
                return songsService.getSongById(songId)
            } catch (e: Exception) {
                /* ignore */
            }
        }
        throw IllegalArgumentException("Couldn't find song with id: $songId")
    }

    suspend fun searchSong(query: String): List<Song> {
        val result = mutableListOf<Song>()
        for (songsService in songsServices) {
            result.addAll(songsService.searchSongs(query))
        }
        return result
    }

    suspend fun createOrUpdateSong(song: Song) {
        for (songsService in songsServices) {
            try {
                songsService.createOrUpdateSong(song)
            } catch (e: Exception) {
                /* ignore */
            }
        }
    }

    suspend fun getRandomSong(): Song {
        for (songsService in songsServices) {
            val songs = songsService.getAllSongs()
            if (songs.isEmpty()) continue
            val randomIdx = abs(Random.nextInt()) % songs.size
            return songs[randomIdx]
        }
        throw Exception()
    }

    suspend fun addSongToLastPlayed(song: Song) {
        for (songsService in songsServices) {
            songsService.addToLastPlayed(song)
        }
    }

}