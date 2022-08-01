package com.roman.kubik.songer.songs.domain.repository

import com.roman.kubik.songer.core.AppResult
import com.roman.kubik.songer.songs.domain.song.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.abs
import kotlin.random.Random

class SongRepository @Inject constructor(
        private val songsService: SongsService,
        private val songSearcherProvider: SongSearcherProvider,
        private val songsUpdateService: SongsUpdateService
) {

    private val songsSearchers: Set<SongsSearcher>
        get() = songSearcherProvider.getSongSearchers()

    init {
        GlobalScope.launch(Dispatchers.IO) {
            fetchNewSongs(false)
        }
    }

    suspend fun getAllSongs(): AppResult<List<Song>> {
        return try {
            AppResult.Success(songsService.getAllSongs())
        } catch (e: Throwable) {
            AppResult.Error(e)
        }
    }

    suspend fun getAllSongs(category: SongCategory): AppResult<List<Song>> {
        return try {
            val songs = when (category) {
                SongCategory.LAST_PLAYED -> songsService.getLastPlayedSongs()
                SongCategory.FAVOURITE -> songsService.getFavouriteSongs()
                SongCategory.LAST_ADDED -> songsService.getLastAddedSongs()
                else -> songsService.getAllSongs(category)
            }
            AppResult.Success(songs)
        } catch (e: Throwable) {
            AppResult.Error(e)
        }
    }

    suspend fun getSongById(songId: String): AppResult<Song> {
        for (songsSearcher in songsSearchers) {
            try {
                return AppResult.Success(songsSearcher.getSongById(songId))
            } catch (e: Exception) {
                /* ignore */
            }
        }
        return AppResult.Error(IllegalArgumentException("Couldn't find song with id: $songId"))
    }

    fun searchSongFlow(query: String) = channelFlow {
        var success = false
        val resultPairs = mutableListOf<Pair<Int, List<Song>>>()
        coroutineScope {
            songsSearchers.forEachIndexed { index, songsSearcher ->
                launch {
                    try {
                        val songList = songsSearcher.searchSongs(query)
                        if (index == 0) {
                            offer(AppResult.Success(songList))
                        }
                        resultPairs.add(index to songList)
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

        if (success) offer(AppResult.Success(result)) else offer(AppResult.Error(Exception()))
    }

    suspend fun createOrUpdateSong(song: Song): AppResult<Any> {
        return try {
            AppResult.Success(songsService.createOrUpdateSong(song))
        } catch (e: Throwable) {
            AppResult.Error(e)
        }
    }

    suspend fun getRandomSong(): AppResult<Song> {
        return try {
            val songs = songsService.getAllSongs()
            val randomIdx = abs(Random.nextInt()) % songs.size
            return AppResult.Success(songs[randomIdx])
        } catch (e: Throwable) {
            AppResult.Error(e)
        }
    }

    suspend fun addSongToLastPlayed(song: Song) {
        songsService.addToLastPlayed(song)
    }

    suspend fun removeSong(song: Song): AppResult<Any> {
        return try {
            AppResult.Success(songsService.removeSong(song))
        } catch (e: Throwable) {
            AppResult.Error(e)
        }
    }

    suspend fun fetchNewSongs(forceFetch: Boolean) {
        when (val res = songsUpdateService.fetchNewSongs(forceFetch)) {
            is AppResult.Success -> {
                for (s in res.data) {
                    createOrUpdateSong(s)
                }
                songsService.clearLastAddedSongs()
                songsService.addSongsToLastAdded(res.data)
            }
            else -> {
                /* ignore */
            }
        }
    }

    suspend fun derussify() {
        songsService.derussify()
    }
}