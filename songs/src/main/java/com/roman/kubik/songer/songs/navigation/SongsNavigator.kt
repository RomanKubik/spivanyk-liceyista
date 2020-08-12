package com.roman.kubik.songer.songs.navigation

import com.roman.kubik.songer.core.navigation.BaseNavigator
import com.roman.kubik.songer.songs.domain.song.Song

interface SongsNavigator : BaseNavigator {

    fun navigateToSongDetails(songId: String)

    fun navigateToEditSong(songId: String)

    fun shareSong(song: Song)

}