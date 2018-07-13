package com.roman.kubik.songer.presentation.main

import com.roman.kubik.songer.domain.song.Song

interface MainContract {

    interface View {
        fun setPatrioticsCount(count: Int)
        fun setBonfiresCount(count: Int)
        fun setAbroadsCount(count: Int)
        fun setAllCount(count: Int)
        fun setFavouriteCount(count: Int)
        fun showError(error: Throwable)
        fun navigateToSong(song: Song)
    }

    interface Presenter {
        fun requestData()
        fun requestRandom()
        fun destroy()
    }
}