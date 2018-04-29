package com.roman.kubik.spivanyklicejista.presentation.edit

import com.roman.kubik.spivanyklicejista.domain.song.Song

interface EditSongContract {

    interface View {

        fun showSong(song: Song)

        fun onChordsRecognized(lyrics: String)

        fun showProgress(show: Boolean)

        fun showError(text: String)
    }

    interface Presenter {

        fun fetchSong(songId: Int)

        fun recognizeChords(lyrics: String)

        fun destroy()
    }
}