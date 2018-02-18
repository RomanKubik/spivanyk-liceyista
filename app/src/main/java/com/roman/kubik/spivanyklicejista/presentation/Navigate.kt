package com.roman.kubik.spivanyklicejista.presentation

import android.app.Activity
import android.content.Intent
import com.roman.kubik.spivanyklicejista.Constants
import com.roman.kubik.spivanyklicejista.domain.song.Song

import com.roman.kubik.spivanyklicejista.presentation.main.MainActivity
import com.roman.kubik.spivanyklicejista.presentation.song.SongActivity

/**
 * Application router. Provides methods to navigate from one activity to another
 * Created by kubik on 1/14/18.
 */

object Navigate {

    fun toMainActivity(activity: Activity) {
        activity.startActivity(Intent(activity, MainActivity::class.java))
    }

    fun toSongActivity(activity: Activity, song: Song) {
        val intent = Intent(activity, SongActivity::class.java)
        intent.putExtra(Constants.Extras.SONG_ID, song.id)
        activity.startActivity(intent)
    }
}
