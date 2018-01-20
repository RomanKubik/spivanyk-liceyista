package com.roman.kubik.spivanyklicejista.presentation.main;

import com.roman.kubik.spivanyklicejista.domain.song.Song;

import java.util.List;

/**
 * Contract between {@link MainActivity} and {@link MainPresenter}
 * Created by kubik on 1/14/18.
 */

public interface MainContract {

    interface View {
        void showProgress(boolean show);
        void showError(String errorMessage);
        void onSongsFetched(List<Song> songList);
    }

    interface Presenter {
        void fetchAllSongs();
    }
}
