package com.roman.kubik.songer.presentation.list

import com.roman.kubik.songer.domain.song.Song

/**
 * Contract between [ListActivity] and [ListPresenter]
 * Created by kubik on 1/14/18.
 */

interface ListContract {

    interface View {
        fun showProgress(show: Boolean)
        fun showError(errorMessage: String?)
        fun onPreferencesFetched(showChords: Boolean)
        fun onSongsFetched(songList: List<Song>)
        fun onSongRemoved(song: Song)
        fun showDeletionTutorialDialog()
        fun showInfo(state: InfoState)
    }

    interface Presenter {
        fun showSong(song: Song)
        fun deleteSong(song: Song)
        fun undoDeletion()
        fun fetchPreferences()
        fun fetchSongByCategory(categoryId: Int)
        fun filter(query: String)
        fun destroy()
        fun onTutorialDialogShowed()
    }

    enum class InfoState {
        OK, NOT_FOUND, WEB_PLACEHOLDER
    }
}
