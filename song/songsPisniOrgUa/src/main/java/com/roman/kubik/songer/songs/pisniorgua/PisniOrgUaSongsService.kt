package com.roman.kubik.songer.songs.pisniorgua

import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.domain.song.SongCategory
import com.roman.kubik.songer.songs.domain.song.SongsService
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.net.URLEncoder
import javax.inject.Inject

class PisniOrgUaSongsService @Inject constructor() : SongsService {

    companion object {
        private const val BASE_URL = "https://www.pisni.org.ua"
        private const val SEARCH_URL = "/search.php?phrase=%s&obj=s"
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

    override suspend fun createOrUpdateSong(song: Song) {
    }

    override suspend fun addToLastPlayed(song: Song) {
    }

    override suspend fun removeSong(song: Song) {
    }
}