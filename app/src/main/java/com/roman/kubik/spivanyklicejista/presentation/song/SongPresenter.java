package com.roman.kubik.spivanyklicejista.presentation.song;

import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor;

import javax.inject.Inject;

/**
 * Created by kubik on 1/20/18.
 */

public class SongPresenter implements SongContract.Presenter {


    @Inject
    public SongPresenter(SongContract.View activity, SongInteractor songInteractor) {

    }

    @Override
    public void fetchSong(int id) {

    }
}
