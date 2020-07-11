package com.roman.kubik.songer.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.roman.kubik.songer.R
import com.roman.kubik.songer.home.navigation.HomeNavigator
import com.roman.kubik.songer.home.ui.HomeCategory
import com.roman.kubik.songer.songs.navigation.SongsNavigator
import com.roman.kubik.songer.songs.ui.details.SongDetailsFragment
import javax.inject.Inject

class AppNavigator @Inject constructor(private val navController: NavController) : HomeNavigator, SongsNavigator {

    override fun navigateToCategory(category: HomeCategory) {
        navController.navigate(R.id.action_menu_home_to_menu_discover)
    }

    override fun navigateToSongDetails(songId: String) {
        val args = bundleOf(SongDetailsFragment.ARG_SONG_ID to songId)
        navController.navigate(R.id.action_menu_discover_to_songDetailsFragment, args)
    }
}