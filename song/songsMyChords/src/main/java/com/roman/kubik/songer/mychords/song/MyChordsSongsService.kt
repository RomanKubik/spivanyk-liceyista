package com.roman.kubik.songer.mychords.song

import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.domain.song.SongsSearcher
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject


class MyChordsSongsService @Inject constructor(): SongsSearcher {

    companion object {
        private const val BASE_URL = "https://mychords.net"
        private const val SEARCH_URL = "/search?q="
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