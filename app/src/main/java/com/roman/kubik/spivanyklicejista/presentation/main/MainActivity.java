package com.roman.kubik.spivanyklicejista.presentation.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.roman.kubik.spivanyklicejista.R;
import com.roman.kubik.spivanyklicejista.domain.song.Song;
import com.roman.kubik.spivanyklicejista.presentation.BaseActivity;
import com.roman.kubik.spivanyklicejista.presentation.Navigate;
import com.roman.kubik.spivanyklicejista.presentation.main.di.MainModule;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.roman.kubik.spivanyklicejista.general.android.SpivanykApplication.component;

/**
 * Main activity
 * Created by kubik on 1/14/18.
 */

public class MainActivity extends BaseActivity implements MainContract.View {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.songList)
    RecyclerView songList;

    @Inject
    MainContract.Presenter presenter;
    @Inject
    SongsAdapter songsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        component.mainComponent(new MainModule(this)).inject(this);
        init();
        presenter.fetchAllSongs();
    }

    @Override
    public void showProgress(boolean show) {
        Log.d(TAG, "showProgress: " + show);
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError(String errorMessage) {
        Log.d(TAG, "showError: " + errorMessage);
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSongsFetched(List<Song> songList) {
        Log.d(TAG, "onSongsFetched: " + songList.size());
        songsAdapter.addSongList(songList);
    }

    private void init() {
        songsAdapter.setOnClickListener(s -> {
            Log.d(TAG, "songClicked: " + s.getTitle());
            Navigate.toSongActivity(this, s);
        });
        songList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        songList.setAdapter(songsAdapter);
    }
}
