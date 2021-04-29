package com.roman.kubik.songer.songs.pisniorgua

import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.domain.song.SongsSearcher
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.net.URLEncoder
import javax.inject.Inject

class PisniOrgUaSongsService @Inject constructor() : SongsSearcher {

    companion object {
        private const val BASE_URL = "https://www.pisni.org.ua"
        private const val SEARCH_URL = "/search.php?phrase=%s&obj=s"
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun searchSongs(query: String): List<Song> {
        val q = URLEncoder.encode(query, "windows-1251")
        val document: Document = Jsoup
                .connect(BASE_URL + SEARCH_URL.format(q))
                .get()

        return PisniOrgUaParser.parseSongsList(document)
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun getSongById(songId: String): Song {
        val document = Jsoup.connect(BASE_URL + songId).get()

        return PisniOrgUaParser.parseSongDetails(document, songId)
    }

}