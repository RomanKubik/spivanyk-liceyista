package com.roman.kubik.spivanyklicejista.presentation.main

import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor
import com.roman.kubik.spivanyklicejista.general.di.ActivityScope

import javax.inject.Inject

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Presenter for main activity
 * Created by kubik on 1/14/18.
 */

@ActivityScope
class MainPresenter @Inject
constructor(private val view: MainContract.View, private val songInteractor: SongInteractor) : MainContract.Presenter {

    override fun fetchAllSongs() {
        view.showProgress(true)
        songInteractor.all
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { view.showProgress(false) }
                .subscribe({ r -> view.onSongsFetched(r) }
                ) { t -> view.showError(t.message) }
    }
}
