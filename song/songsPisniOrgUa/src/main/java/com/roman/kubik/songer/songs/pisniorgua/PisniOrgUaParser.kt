package com.roman.kubik.songer.songs.pisniorgua

import com.roman.kubik.songer.chords.ChordsRecognizer
import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.domain.song.SongCategory
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


object PisniOrgUaParser {

    private const val SOURCE_NAME = "pisni.org.ua"

    private const val SONG_TABLE_ITEM = "a.dyn"
    private const val SONG_TABLE_ITEM_ID = "href"
    private const val SONG_TABLE_ID = "/songs/"
    private const val SONG_TITLE_ITEM = "h1.nomarg"
    private const val SONG_LYRICS_ITEM = "pre.songwords"

    fun parseSongsList(document: Document): List<Song> {
        val elements: Elements = document.select(SONG_TABLE_ITEM)

        val songs: MutableList<Song> = ArrayList()

        for (i in 0 until elements.size) {
            try {
                val songId = elements[i].attr(SONG_TABLE_ITEM_ID)
                if (!songId.contains(SONG_TABLE_ID)) continue
                songs.add(Song(songId, elements[i].text(), "", SongCategory.WEB, false, SOURCE_NAME))
            } catch (e: Exception) {
                /* ignore exception */
            }
        }
        return songs
    }

    fun parseSongDetails(document: Document, songId: String): Song {
        val title = document.selectFirst(SONG_TITLE_ITEM).text()
        val lyrics: String = ChordsRecognizer.recognizeChords(document
                .selectFirst(SONG_LYRICS_ITEM)
                .text())
        return Song(songId, title, lyrics, SongCategory.WEB, false, SOURCE_NAME)
    }

}