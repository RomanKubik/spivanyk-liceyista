package com.roman.kubik.spivanyklicejista.presentation.song

import com.roman.kubik.spivanyklicejista.domain.song.Song

/**
 * Created by kubik on 1/20/18.
 */

interface SongContract {

    interface View {
        fun showSong(song: Song)

        fun showError(errorMessage: String)

        fun isFavouriteSong(isFavourite: Boolean)
    }

    interface Presenter {
        fun fetchSong(id: Int)

        fun addToFavourite()

        fun isFavouriteSong()
    }
}
