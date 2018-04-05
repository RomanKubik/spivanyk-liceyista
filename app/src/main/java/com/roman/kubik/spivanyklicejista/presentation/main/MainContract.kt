package com.roman.kubik.spivanyklicejista.presentation.main

interface MainContract {

    interface View {
        fun setPatrioticsCount(count: Int)
        fun setBonfiresCount(count: Int)
        fun setAbroadsCount(count: Int)
        fun setAllCount(count: Int)
        fun showError(error: Throwable)
    }

    interface Presenter {
        fun requestData()
        fun requestRandom()
        fun onDestroy()
    }
}