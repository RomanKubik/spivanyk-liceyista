package com.roman.kubik.songer.presentation.edit

import com.roman.kubik.songer.domain.song.Song
import com.roman.kubik.songer.presentation.tutorial.TutorialType

interface EditSongContract {

    interface View {

        fun showSong(song: Song)

        fun onChordsRecognized(lyrics: CharSequence)

        fun onSongSaved()

        fun showProgress(show: Boolean)

        fun showError(text: String)

        fun showTutorial(tutorialType: TutorialType)
    }

    interface Presenter {

        fun fetchSong(songId: Int)

        fun recognizeChords(lyrics: String)

        fun saveSong(title: String, lyrics: String)

        fun tutorialShown(tutorialType: TutorialType)

        fun destroy()
    }
}