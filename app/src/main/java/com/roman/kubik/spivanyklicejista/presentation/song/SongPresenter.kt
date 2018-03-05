package com.roman.kubik.spivanyklicejista.presentation.song

import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor

import javax.inject.Inject

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by kubik on 1/20/18.
 */

class SongPresenter @Inject
constructor(private val view: SongContract.View, private val songInteractor: SongInteractor) : SongContract.Presenter {

    override fun fetchSong(id: Int) {
        songInteractor.getById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ s -> view.showSong(s) }
                ) { t -> view.showError(t.message!!) }
    }
}
