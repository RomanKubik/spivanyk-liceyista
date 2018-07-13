package com.roman.kubik.spivanyklicejista.presentation.song

import com.roman.kubik.spivanyklicejista.domain.category.Category
import com.roman.kubik.spivanyklicejista.domain.chord.Chord
import com.roman.kubik.spivanyklicejista.domain.song.Song

/**
 * Created by kubik on 1/20/18.
 */

interface SongContract {

    interface View {
        fun setSongTitle(title: CharSequence)
        fun setSongLyrics(lyrics: CharSequence)
        fun setSongCategory(category: Category)
        fun setSongDifficulty(difficulty: CharSequence)
        fun setSongChords(chords: List<Chord>)
        fun isFavouriteSong(isFavourite: Boolean)
        fun setChordsVisibility(visible: Boolean)
        fun showChord(chord: String)
        fun share(type: String, title: String, lyrics: String)
        fun edit(song: Song)
        fun showProgress(show: Boolean)
        fun showError(errorMessage: String)
    }

    interface Presenter {
        fun setChordColors(textColor: Int, backgroundColor: Int)
        fun fetchSong(id: Int)
        fun fetchPreferences()
        fun addToFavourite()
        fun shareSong()
        fun edit()
        fun showChords()
        fun detroy()
    }
}
