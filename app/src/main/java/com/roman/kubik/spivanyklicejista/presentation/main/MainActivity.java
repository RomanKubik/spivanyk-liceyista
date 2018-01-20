package com.roman.kubik.spivanyklicejista.presentation.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.roman.kubik.spivanyklicejista.R;
import com.roman.kubik.spivanyklicejista.domain.song.Song;
import com.roman.kubik.spivanyklicejista.presentation.main.di.MainModule;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.roman.kubik.spivanyklicejista.general.android.SpivanykApplication.component;

/**
 * Main activity
 * Created by kubik on 1/14/18.
 */

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    MainContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component.mainComponent(new MainModule(this)).inject(this);
        ButterKnife.bind(this);
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
    }

    @OnClick(R.id.button)
    void onClicked() {
        presenter.fetchAllSongs();
    }
}
