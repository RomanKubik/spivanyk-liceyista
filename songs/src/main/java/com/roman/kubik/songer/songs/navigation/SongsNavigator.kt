package com.roman.kubik.songer.songs.navigation

import com.roman.kubik.songer.core.navigation.BaseNavigator

interface SongsNavigator: BaseNavigator {

    fun navigateToSongDetails(songId: String)

    fun navigateToEditSong(songId: String)

}