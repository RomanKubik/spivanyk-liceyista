package com.roman.kubik.songer.presentation

import android.app.Activity
import android.content.Intent
import com.roman.kubik.songer.Constants
import com.roman.kubik.songer.domain.song.Song
import com.roman.kubik.songer.presentation.edit.EditSongActivity

import com.roman.kubik.songer.presentation.list.ListActivity
import com.roman.kubik.songer.presentation.main.MainActivity
import com.roman.kubik.songer.presentation.preferences.PreferencesActivity
import com.roman.kubik.songer.presentation.song.SongActivity

/**
 * Application router. Provides methods to navigate from one activity to another
 * Created by kubik on 1/14/18.
 */

object Navigate {

    fun toMainActivity(activity: Activity) {
        activity.startActivity(Intent(activity, MainActivity::class.java))
    }

    fun toListActivity(activity: Activity, categoryId: Int) {
        val intent = Intent(activity, ListActivity::class.java)
        intent.putExtra(Constants.Extras.CATEGORY_ID, categoryId)
        activity.startActivity(intent)
    }

    fun toSongActivity(activity: Activity, song: Song) {
        val intent = Intent(activity, SongActivity::class.java)
        intent.putExtra(Constants.Extras.SONG_ID, song.id)
        activity.startActivity(intent)
    }

    fun toPreferencesActivity(activity: Activity) {
        activity.startActivity(Intent(activity, PreferencesActivity::class.java))
    }

    fun toEditActivity(activity: Activity) {
        val intent = Intent(activity, EditSongActivity::class.java)
        activity.startActivityForResult(intent, Constants.RequestCode.EDIT_SONG)
    }

    fun toEditActivity(activity: Activity, song: Song) {
        val intent = Intent(activity, EditSongActivity::class.java)
        intent.putExtra(Constants.Extras.SONG_ID, song.id)
        activity.startActivityForResult(intent, Constants.RequestCode.EDIT_SONG)
    }
}
