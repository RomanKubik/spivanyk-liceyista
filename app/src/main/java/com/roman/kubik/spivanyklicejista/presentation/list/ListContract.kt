package com.roman.kubik.spivanyklicejista.presentation.list

import com.roman.kubik.spivanyklicejista.domain.song.Song

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
    }

    interface Presenter {
        fun fetchPreferences()
        fun fetchSongByCategory(categoryId: Int)
        fun filter(byTitle: String)
    }
}
