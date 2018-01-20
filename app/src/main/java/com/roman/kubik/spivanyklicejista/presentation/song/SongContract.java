package com.roman.kubik.spivanyklicejista.presentation.song;

import com.roman.kubik.spivanyklicejista.domain.song.Song;

/**
 * Created by kubik on 1/20/18.
 */

public interface SongContract {

    interface View {
        void showSong(Song song);

        void showError(String errorMessage);
    }

    interface Presenter {
        void fetchSong(int id);
    }
}
