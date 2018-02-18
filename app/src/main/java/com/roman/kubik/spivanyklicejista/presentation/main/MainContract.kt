package com.roman.kubik.spivanyklicejista.presentation.main

import com.roman.kubik.spivanyklicejista.domain.song.Song

/**
 * Contract between [MainActivity] and [MainPresenter]
 * Created by kubik on 1/14/18.
 */

interface MainContract {

    interface View {
        fun showProgress(show: Boolean)
        fun showError(errorMessage: String?)
        fun onSongsFetched(songList: List<Song>)
    }

    interface Presenter {
        fun fetchAllSongs()
    }
}
