package com.roman.kubik.songer.navigation

import android.app.Activity
import android.content.Intent
import com.roman.kubik.songer.Constants

import com.roman.kubik.songer.domain.navigation.NavigationService
import com.roman.kubik.songer.domain.song.Song
import com.roman.kubik.songer.presentation.edit.EditSongActivity
import com.roman.kubik.songer.presentation.list.ListActivity
import com.roman.kubik.songer.presentation.main.MainActivity
import com.roman.kubik.songer.presentation.preferences.PreferencesActivity
import com.roman.kubik.songer.presentation.song.SongActivity

class NavigationServiceImpl(private val activity: Activity) : NavigationService {

    override fun toMainActivity() {
        activity.startActivity(Intent(activity, MainActivity::class.java))
    }

    override fun toListActivity(categoryId: Int) {
        val intent = Intent(activity, ListActivity::class.java)
        intent.putExtra(Constants.Extras.CATEGORY_ID, categoryId)
        activity.startActivity(intent)
    }

    override fun toSongActivity(song: Song) {
        val intent = Intent(activity, SongActivity::class.java)
        intent.putExtra(Constants.Extras.SONG_ID, song.id)
        activity.startActivity(intent)
    }

    override fun toPreferencesActivity() {
        activity.startActivity(Intent(activity, PreferencesActivity::class.java))
    }

    override fun toAddSongActivity() {
        val intent = Intent(activity, EditSongActivity::class.java)
        activity.startActivityForResult(intent, Constants.RequestCode.EDIT_SONG)
    }

    override fun toEditActivity(song: Song) {
        val intent = Intent(activity, EditSongActivity::class.java)
        intent.putExtra(Constants.Extras.SONG_ID, song.id)
        activity.startActivityForResult(intent, Constants.RequestCode.EDIT_SONG)
    }

    override fun toShareText(title: String?, text: String?) {
        val intent = Intent()
                .setAction(Intent.ACTION_SEND)
                .setType(Constants.SHARE_TEXT_TYPE)
                .putExtra(Intent.EXTRA_TITLE, title)
                .putExtra(Intent.EXTRA_TEXT, text)
        activity.startActivity(intent)
    }

}
