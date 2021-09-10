package com.roman.kubik.songer.mychords.song

import com.roman.kubik.songer.chords.ChordsRecognizer
import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.domain.song.SongCategory
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


object MyChordsParser {

    private const val SOURCE_NAME = "mychords.net"

    private const val SONG_TABLE_ITEM = "a.b-listing__item__link"
    private const val SONG_TABLE_ITEM_ID = "href"
    private const val SONG_TITLE_ITEM = "h1.b-title"
    private const val SONG_LYRICS_ITEM = "pre.w-words__text"
    private const val LYRICS_COPYRIGHT = "Взято с сайта https://mychords.net"

    fun parseSongsList(document: Document): List<Song> {
        val elements: Elements = document.select(SONG_TABLE_ITEM)

        val songs: MutableList<Song> = ArrayList()

        for (i in 0 until elements.size) {
            try {
                songs.add(Song(elements[i].attr(SONG_TABLE_ITEM_ID), elements[i].text(), "", SongCategory.WEB, false, SOURCE_NAME))
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
                .text()
                .replace(LYRICS_COPYRIGHT, ""))
        return Song(songId, title, lyrics, SongCategory.WEB, false, SOURCE_NAME)
    }

}