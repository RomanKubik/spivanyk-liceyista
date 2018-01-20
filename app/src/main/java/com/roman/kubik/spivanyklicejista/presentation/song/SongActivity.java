package com.roman.kubik.spivanyklicejista.presentation.song;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.roman.kubik.spivanyklicejista.Constants;
import com.roman.kubik.spivanyklicejista.R;
import com.roman.kubik.spivanyklicejista.domain.song.Song;
import com.roman.kubik.spivanyklicejista.general.android.SpivanykApplication;
import com.roman.kubik.spivanyklicejista.presentation.BaseActivity;
import com.roman.kubik.spivanyklicejista.presentation.song.di.SongModule;

import javax.inject.Inject;

/**
 * Created by kubik on 1/20/18.
 */

public class SongActivity extends BaseActivity implements SongContract.View {

    private static final String TAG = SongActivity.class.getSimpleName();

    @Inject
    SongContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        SpivanykApplication.component.songComponent(new SongModule(this)).inject(this);
        init();
    }

    @Override
    public void showSong(Song song) {
        Log.d(TAG, "showSong: " + song.getTitle());
    }

    @Override
    public void showError(String errorMessage) {
        Log.d(TAG, "showError: " + errorMessage);
    }

    private void init() {
        presenter.fetchSong(getIntent().getIntExtra(Constants.Extras.SONG_ID, 0));
    }
}
