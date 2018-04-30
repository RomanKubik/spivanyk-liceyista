package com.roman.kubik.spivanyklicejista.presentation.edit

import com.roman.kubik.spivanyklicejista.Constants
import com.roman.kubik.spivanyklicejista.domain.formatting.LyricsFormattingInteractor
import com.roman.kubik.spivanyklicejista.domain.song.Song
import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class EditSongPresenter @Inject constructor(
        private val view: EditSongContract.View,
        private val songInteractor: SongInteractor,
        private val lyricsFormattingInteractor: LyricsFormattingInteractor,
        private val compositeDisposable: CompositeDisposable) : EditSongContract.Presenter {

    private var song: Song? = null

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
        view.onChordsRecognized(lyricsFormattingInteractor.recognizeChords(lyrics))
    }

    override fun saveSong(title: String, lyrics: String) {
        view.showProgress(true)
        if (song == null) {
            song = Song(UUID.randomUUID().hashCode(), title, lyrics, Constants.Category.USERS_ID)
        } else {
            song?.title = title
            song?.lyrics = lyrics
        }
        compositeDisposable.add(songInteractor.insertOrUpdate(song)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { view.showProgress(false) }
                .subscribe(view::onSongSaved,
                        { t -> view.showError(t.message!!) }))
    }

    override fun destroy() {
        compositeDisposable.clear()
    }
}