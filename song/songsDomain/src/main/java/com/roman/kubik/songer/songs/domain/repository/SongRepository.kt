package com.roman.kubik.songer.songs.domain.repository

import com.roman.kubik.songer.core.AppResult
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

    suspend fun getAllSongs(): AppResult<List<Song>> {
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

        return AppResult.Success(result)
    }

    suspend fun getAllSongs(category: SongCategory): AppResult<List<Song>> {
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

        return AppResult.Success(result)
    }

    suspend fun getSongById(songId: String): AppResult<Song> {
        for (songsService in songsServices) {
            try {
                return AppResult.Success(songsService.getSongById(songId))
            } catch (e: Exception) {
                /* ignore */
            }
        }
        return AppResult.Error(IllegalArgumentException("Couldn't find song with id: $songId"))
    }

    suspend fun searchSong(query: String): AppResult<List<Song>> {
        var success = false
        val resultPairs = mutableListOf<Pair<Int, List<Song>>>()
        coroutineScope {
            songsServices.forEachIndexed { index, songsService ->
                launch {
                    try {
                        resultPairs.add(index to songsService.searchSongs(query))
                        success = true
                    } catch (e: Exception) {
                        /* ignore */
                    }
                }
            }
        }
        resultPairs.sortBy { it.first }

        val result = mutableListOf<Song>()
        resultPairs.forEach { pair ->
            result.addAll(pair.second)
        }

        return if (success) AppResult.Success(result) else AppResult.Error(Exception())
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

    //    suspend fun createOrUpdateSong(song: Song): AppResult<Any> {
//        var success = false
//        coroutineScope {
//            songsServices.forEach { songsService ->
//                launch {
//                    try {
//                        songsService.createOrUpdateSong(song)
//                        success = true
//                    } catch (e: Exception) {
//                        /* ignore */
//                    }
//                }
//            }
//        }
//        return if (success) AppResult.Success(Any()) else AppResult.Error(Exception())
//    }

    suspend fun getRandomSong(): Song {
        for (songsService in songsServices) {
            val songs = songsService.getAllSongs()
            if (songs.isEmpty()) continue
            val randomIdx = abs(Random.nextInt()) % songs.size
            return songs[randomIdx]
        }
        throw Exception()
    }

//    suspend fun getRandomSong(): AppResult<Song> {
//        for (songsService in songsServices) {
//            val songs = songsService.getAllSongs()
//            if (songs.isEmpty()) continue
//            val randomIdx = abs(Random.nextInt()) % songs.size
//            return AppResult.Success(songs[randomIdx])
//        }
//        return AppResult.Error(Exception())
//    }

    suspend fun addSongToLastPlayed(song: Song) {
        coroutineScope {
            songsServices.forEach { songsService ->
                launch { songsService.addToLastPlayed(song) }
            }
        }
    }

    suspend fun removeSong(song: Song): AppResult<Any> {
        coroutineScope {
            songsServices.forEach { songsService ->
                launch { songsService.removeSong(song) }
            }
        }
        return AppResult.Success(Any())
    }

}