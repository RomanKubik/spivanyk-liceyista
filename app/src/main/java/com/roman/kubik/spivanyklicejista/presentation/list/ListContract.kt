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
        fun onSongsFetched(songList: List<Song>)
    }

    interface Presenter {
        fun fetchAllSongs()
        fun filter(byTitle: String)
    }
}
