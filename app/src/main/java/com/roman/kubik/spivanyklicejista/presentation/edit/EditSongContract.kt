package com.roman.kubik.spivanyklicejista.presentation.edit

interface EditSongContract {

    interface View {

        fun showProgress(show: Boolean)

        fun showError(text: String)
    }

    interface Presenter {

        fun destroy()
    }
}