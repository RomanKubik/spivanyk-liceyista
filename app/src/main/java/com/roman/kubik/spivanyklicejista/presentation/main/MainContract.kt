package com.roman.kubik.spivanyklicejista.presentation.main

import com.roman.kubik.spivanyklicejista.domain.song.Song

interface MainContract {

    interface View {
        fun setPatrioticsCount(count: Int)
        fun setBonfiresCount(count: Int)
        fun setAbroadsCount(count: Int)
        fun setAllCount(count: Int)
        fun showError(error: Throwable)
        fun navigateToSong(song: Song)
    }

    interface Presenter {
        fun requestData()
        fun requestRandom()
        fun destroy()
    }
}