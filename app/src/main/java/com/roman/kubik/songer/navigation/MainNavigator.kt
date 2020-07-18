package com.roman.kubik.songer.navigation

import androidx.navigation.fragment.NavHostFragment

interface MainNavigator {

    fun setNavHostFragment(navHostFragment: NavHostFragment)

    fun navigateToSongDetails(songId: String)

}