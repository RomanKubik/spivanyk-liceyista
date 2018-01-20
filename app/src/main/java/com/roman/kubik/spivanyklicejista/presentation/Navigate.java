package com.roman.kubik.spivanyklicejista.presentation;

import android.app.Activity;
import android.content.Intent;

import com.roman.kubik.spivanyklicejista.Constants;
import com.roman.kubik.spivanyklicejista.domain.song.Song;
import com.roman.kubik.spivanyklicejista.presentation.main.MainActivity;
import com.roman.kubik.spivanyklicejista.presentation.song.SongActivity;

/**
 * Application router. Provides methods to navigate from one activity to another
 * Created by kubik on 1/14/18.
 */

public final class Navigate {

    public static void toMainActivity(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

    public static void toSongActivity(Activity activity, Song song) {
        Intent intent = new Intent(activity, SongActivity.class);
        intent.putExtra(Constants.Extras.SONG_ID, song.getId());
        activity.startActivity(intent);
    }
}
