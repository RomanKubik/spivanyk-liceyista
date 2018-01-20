package com.roman.kubik.spivanyklicejista.presentation.song;

import android.os.Bundle;
import android.support.annotation.Nullable;

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

    @Inject
    SongContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        SpivanykApplication.component.songComponent(new SongModule(this)).inject(this);
    }

    @Override
    public void showSong(Song song) {

    }

    @Override
    public void showError(String errorMessage) {

    }
}
