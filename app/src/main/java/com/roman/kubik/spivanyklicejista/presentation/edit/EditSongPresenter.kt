package com.roman.kubik.spivanyklicejista.presentation.edit

import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class EditSongPresenter @Inject constructor(
        private val songInteractor: SongInteractor,
        private val compositeDisposable: CompositeDisposable) : EditSongContract.Presenter {



    override fun destroy() {
        compositeDisposable.clear()
    }
}