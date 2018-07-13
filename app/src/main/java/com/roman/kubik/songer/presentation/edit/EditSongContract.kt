package com.roman.kubik.songer.presentation.edit

import com.roman.kubik.songer.domain.song.Song

interface EditSongContract {

    interface View {

        fun showSong(song: Song)

        fun onChordsRecognized(lyrics: CharSequence)

        fun onSongSaved()

        fun showProgress(show: Boolean)

        fun showError(text: String)
    }

    interface Presenter {

        fun fetchSong(songId: Int)

        fun recognizeChords(lyrics: String)

        fun saveSong(title: String, lyrics: String)

        fun destroy()
    }
}