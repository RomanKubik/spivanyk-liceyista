package com.roman.kubik.spivanyklicejista.presentation.main;

import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor;
import com.roman.kubik.spivanyklicejista.domain.song.SongRepository;
import com.roman.kubik.spivanyklicejista.general.di.ActivityScope;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Presenter for main activity
 * Created by kubik on 1/14/18.
 */

@ActivityScope
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private SongInteractor songInteractor;

    @Inject
    public MainPresenter(MainContract.View view, SongInteractor songInteractor) {
        this.view = view;
        this.songInteractor = songInteractor;
    }

    @Override
    public void fetchAllSongs() {
        view.showProgress(true);
        songInteractor.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> view.showProgress(false))
                .subscribe(r -> view.onSongsFetched(r),
                        t -> view.showError(t.getMessage()));
    }
}
