package com.roman.kubik.songer.home.navigation

import com.roman.kubik.songer.core.navigation.BaseNavigator
import com.roman.kubik.songer.songs.domain.song.SongCategory

interface HomeNavigator: BaseNavigator {

    fun navigateToCategory(songCategory: SongCategory)

}