package com.roman.kubik.songer.home.navigation

import com.roman.kubik.songer.songs.domain.song.SongCategory

interface HomeNavigator {

    fun navigateToCategory(songCategory: SongCategory)

}