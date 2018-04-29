package com.roman.kubik.spivanyklicejista.presentation.edit

import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EditSongPresenter @Inject constructor(
        private val view: EditSongContract.View,
        private val songInteractor: SongInteractor,
        private val compositeDisposable: CompositeDisposable) : EditSongContract.Presenter {


    override fun fetchSong(songId: Int) {
        view.showProgress(true)
        compositeDisposable.add(songInteractor.getById(songId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete { view.showProgress(false) }
                .subscribe(view::showSong,
                        { t -> view.showError(t.message!!) }))
    }

    override fun destroy() {
        compositeDisposable.clear()
    }
}