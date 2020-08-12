package com.roman.kubik.songer.navigation

import android.content.Intent
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.roman.kubik.songer.R
import com.roman.kubik.songer.core.navigation.SearchNavigator
import com.roman.kubik.songer.home.navigation.HomeNavigator
import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.domain.song.SongCategory
import com.roman.kubik.songer.songs.navigation.SongsNavigator
import com.roman.kubik.songer.songs.ui.details.SongDetailsFragment
import com.roman.kubik.songer.songs.ui.edit.EditSongFragment
import com.roman.kubik.songer.songs.ui.list.SongsListFragment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppNavigator @Inject constructor() :
        MainNavigator,
        SearchNavigator,
        HomeNavigator,
        SongsNavigator {

    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun setNavHostFragment(navHostFragment: NavHostFragment) {
        this.navHostFragment = navHostFragment
        this.navController = navHostFragment.navController
    }

    override fun navigateToSearch(query: String) {
        val args = bundleOf(SongsListFragment.ARG_QUERY to query)
        if (navHostFragment.childFragmentManager.fragments.last() is SongsListFragment) {
            (navHostFragment.childFragmentManager.fragments.last() as SongsListFragment).searchSongs(query)
        } else {
            navController.navigate(R.id.action_global_menu_discover, args)
        }
    }

    override fun navigateToCategory(songCategory: SongCategory) {
        val args = bundleOf(SongsListFragment.ARG_CATEGORY to songCategory.name)
        navController.navigate(R.id.action_menu_home_to_menu_discover, args)
    }

    override fun navigateToSongDetails(songId: String) {
        val args = bundleOf(SongDetailsFragment.ARG_SONG_ID to songId)
        navController.navigate(R.id.action_global_songDetailsFragment, args)
    }

    override fun navigateToEditSong(songId: String) {
        val args = bundleOf(EditSongFragment.ARG_SONG_ID to songId)
        navController.navigate(R.id.action_songDetailsFragment_to_editSongFragment, args)
    }

    override fun shareSong(song: Song) {
        val intent = Intent()
                .setAction(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_TITLE, song.title)
                .putExtra(Intent.EXTRA_TEXT, song.lyrics)
        navHostFragment.activity?.startActivity(intent)
    }

    override fun navigateUp() {
        navController.navigateUp()
    }
}