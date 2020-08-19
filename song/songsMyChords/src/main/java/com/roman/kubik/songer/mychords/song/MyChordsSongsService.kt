package com.roman.kubik.songer.mychords.song

import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.domain.song.SongCategory
import com.roman.kubik.songer.songs.domain.song.SongsService
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject


class MyChordsSongsService @Inject constructor(): SongsService {

    companion object {
        private const val BASE_URL = "https://mychords.net"
        private const val SEARCH_URL = "/search?q="
    }

    override suspend fun getAllSongs(): List<Song> {
        return emptyList()
    }

    override suspend fun getAllSongs(songCategory: SongCategory): List<Song> {
        return emptyList()
    }

    override suspend fun getLastPlayedSongs(): List<Song> {
        return emptyList()
    }

    override suspend fun getFavouriteSongs(): List<Song> {
        return emptyList()
    }

    override suspend fun createOrUpdateSong(song: Song) {
    }

    override suspend fun addToLastPlayed(song: Song) {
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun searchSongs(query: String): List<Song> {
        val document: Document = Jsoup
                .connect(BASE_URL + SEARCH_URL + query.replace(" ", "+"))
                .get()

        return MyChordsParser.parseSongsList(document)
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun getSongById(songId: String): Song {
        val document = Jsoup.connect(BASE_URL + songId).get()

        return MyChordsParser.parseSongDetails(document, songId)
    }
}