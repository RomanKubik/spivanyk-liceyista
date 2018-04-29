package com.roman.kubik.spivanyklicejista.presentation.edit

import com.roman.kubik.spivanyklicejista.domain.song.Song
import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor
import com.roman.kubik.spivanyklicejista.domain.utils.ChordsRecognizer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EditSongPresenter @Inject constructor(
        private val view: EditSongContract.View,
        private val songInteractor: SongInteractor,
        private val chordsRecognizer: ChordsRecognizer,
        private val compositeDisposable: CompositeDisposable) : EditSongContract.Presenter {

    private lateinit var song: Song

    override fun fetchSong(songId: Int) {
        if (songId == -1) return
        view.showProgress(true)
        compositeDisposable.add(songInteractor.getById(songId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { view.showProgress(false) }
                .doOnSuccess({ s -> song = s })
                .subscribe(view::showSong,
                        { t -> view.showError(t.message!!) }))
    }

    override fun recognizeChords(lyrics: String) {
        view.onChordsRecognized(chordsRecognizer.markChordsInText(lyrics))
    }

    override fun destroy() {
        compositeDisposable.clear()
    }
}