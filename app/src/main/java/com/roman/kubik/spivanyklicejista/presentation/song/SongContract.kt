package com.roman.kubik.spivanyklicejista.presentation.song

import com.roman.kubik.spivanyklicejista.domain.category.Category
import com.roman.kubik.spivanyklicejista.domain.chord.Chord
import com.roman.kubik.spivanyklicejista.domain.song.Song

/**
 * Created by kubik on 1/20/18.
 */

interface SongContract {

    interface View {
        fun showSong(song: Song)

        fun showError(errorMessage: String)

        fun isFavouriteSong(isFavourite: Boolean)

        fun showCategory(category: Category)

        fun showDifficulty(difficulty: String)

        fun showChords(chords: List<Chord>)

        fun share(type: String, title: String, lyrics: String)

        fun edit(song: Song)
    }

    interface Presenter {
        fun fetchSong(id: Int)

        fun addToFavourite()

        fun shareSong()

        fun edit()

        fun showChords()
    }
}
