package com.roman.kubik.spivanyklicejista.presentation.song;

import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kubik on 1/20/18.
 */

public class SongPresenter implements SongContract.Presenter {

    private SongContract.View view;
    private SongInteractor songInteractor;

    @Inject
    public SongPresenter(SongContract.View view, SongInteractor songInteractor) {
        this.view = view;
        this.songInteractor = songInteractor;
    }

    @Override
    public void fetchSong(int id) {
        songInteractor.getById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> view.showSong(s),
                        t -> view.showError(t.getMessage()));
    }
}
