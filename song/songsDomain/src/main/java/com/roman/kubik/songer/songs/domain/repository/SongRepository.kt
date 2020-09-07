package com.roman.kubik.songer.songs.domain.repository

import com.roman.kubik.provider.SongServiceConfigUpdater
import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.domain.song.SongCategory
import com.roman.kubik.songer.songs.domain.song.SongCategory.FAVOURITE
import com.roman.kubik.songer.songs.domain.song.SongCategory.LAST_PLAYED
import com.roman.kubik.songer.songs.domain.song.SongServiceProvider
import com.roman.kubik.songer.songs.domain.song.SongsService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.abs
import kotlin.random.Random

class SongRepository @Inject constructor(private val songServiceProvider: SongServiceProvider) {

    private val songsServices: Set<SongsService>
        get() = songServiceProvider.getSongServices()

    suspend fun getAllSongs(): List<Song> {
        val resultPairs = mutableListOf<Pair<Int, List<Song>>>()
        coroutineScope {
            songsServices.forEachIndexed { index, songsService ->
                launch { resultPairs.add(index to songsService.getAllSongs()) }
            }
        }
        resultPairs.sortBy { it.first }

        val result = mutableListOf<Song>()
        resultPairs.forEach { pair ->
            result.addAll(pair.second)
        }

        return result
    }

    suspend fun getAllSongs(category: SongCategory): List<Song> {
        val resultPairs = mutableListOf<Pair<Int, List<Song>>>()
        coroutineScope {
            songsServices.forEachIndexed { index, songsService ->
                val r = when (category) {
                    LAST_PLAYED -> songsService.getLastPlayedSongs()
                    FAVOURITE -> songsService.getFavouriteSongs()
                    else -> songsService.getAllSongs(category)
                }
                resultPairs.add(index to r)
            }
        }
        resultPairs.sortBy { it.first }

        val result = mutableListOf<Song>()
        resultPairs.forEach { pair ->
            result.addAll(pair.second)
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
        val resultPairs = mutableListOf<Pair<Int, List<Song>>>()
        coroutineScope {
            songsServices.forEachIndexed { index, songsService ->
                launch { resultPairs.add(index to songsService.searchSongs(query)) }
            }
        }
        resultPairs.sortBy { it.first }

        val result = mutableListOf<Song>()
        resultPairs.forEach { pair ->
            result.addAll(pair.second)
        }

        return result
    }

    suspend fun createOrUpdateSong(song: Song) {
        coroutineScope {
            songsServices.forEach { songsService ->
                launch {
                    try {
                        songsService.createOrUpdateSong(song)
                    } catch (e: Exception) {
                        /* ignore */
                    }
                }
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
        coroutineScope {
            songsServices.forEach { songsService ->
                launch { songsService.addToLastPlayed(song) }
            }
        }
    }

    suspend fun removeSong(song: Song) {
        coroutineScope {
            songsServices.forEach { songsService ->
                launch { songsService.removeSong(song) }
            }
        }
    }

}