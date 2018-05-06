package com.roman.kubik.spivanyklicejista.presentation.song

import com.roman.kubik.spivanyklicejista.Constants
import com.roman.kubik.spivanyklicejista.domain.category.CategoryInteractor
import com.roman.kubik.spivanyklicejista.domain.chord.ChordInteractor
import com.roman.kubik.spivanyklicejista.domain.favourite.FavouriteInteractor
import com.roman.kubik.spivanyklicejista.domain.formatting.LyricsFormattingInteractor
import com.roman.kubik.spivanyklicejista.domain.preferences.PreferencesInteractor
import com.roman.kubik.spivanyklicejista.domain.song.Song
import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by kubik on 1/20/18.
 */

class SongPresenter @Inject
constructor(private val view: SongContract.View,
            private val songInteractor: SongInteractor,
            private val favouriteInteractor: FavouriteInteractor,
            private val categoryInteractor: CategoryInteractor,
            private val chordInteractor: ChordInteractor,
            private val preferencesInteractor: PreferencesInteractor,
            private val lyricsFormattingInteractor: LyricsFormattingInteractor,
            private val compositeDisposable: CompositeDisposable) : SongContract.Presenter {

    private lateinit var song: Song

    private var chordColor = -0x1000000
    private var backgroundColor = 0

    private var showChords = true

    override fun setChordColors(textColor: Int, backgroundColor: Int) {
        this.chordColor = textColor
        this.backgroundColor = backgroundColor
    }

    override fun fetchSong(id: Int) {
        compositeDisposable.add(
                songInteractor.getById(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess({ s -> song = s })
                        .doOnSuccess(this::isFavouriteSong)
                        .doOnSuccess(this::getCategory)
                        .doOnSuccess(this::fetchChords)
                        .doOnSuccess({ s -> view.setSongTitle(s.title) })
                        .subscribe({ s -> view.setSongLyrics(formatLyrics(s.lyrics)) }
                        ) { t -> view.showError(t.message!!) })
    }

    override fun fetchPreferences() {
        compositeDisposable.add(
                preferencesInteractor
                        .isChordsVisible
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess({ s -> showChords = s })
                        .subscribe({ s -> view.setChordsVisibility(s) }
                        ) { t -> view.showError(t.message!!) })
    }

    override fun addToFavourite() {
        compositeDisposable.add(
                favouriteInteractor.isInFavouriteList(song)
                        .subscribeOn(Schedulers.io())
                        .subscribe({ b ->
                            if (b) removeSongFromFavourite(song)
                            else addSongToFavourite(song)
                        }))
    }

    override fun shareSong() {
        view.share(Constants.SHARE_TEXT_TYPE, song.title, song.lyrics)
    }

    override fun edit() {
        view.edit(song)
    }

    override fun showChords() {
        compositeDisposable.add(
                preferencesInteractor
                        .switchChordsVisibility()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ fetchPreferences() }))
    }

    override fun detroy() {
        compositeDisposable.clear()
    }

    private fun formatLyrics(lyrics: String): CharSequence {
        return if (showChords)
            lyricsFormattingInteractor.createChords(lyrics, view::showChord, chordColor, backgroundColor)
        else
            lyricsFormattingInteractor.removeChords(lyrics)
    }

    private fun isFavouriteSong(song: Song) {
        compositeDisposable.add(
                favouriteInteractor.isInFavouriteList(song)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::isFavouriteSong, { t -> view.showError(t.message!!) }))
    }

    private fun removeSongFromFavourite(song: Song) {
        compositeDisposable.add(
                favouriteInteractor.removeSong(song)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            view.isFavouriteSong(false)
                        })
    }

    private fun addSongToFavourite(song: Song) {
        compositeDisposable.add(
                favouriteInteractor.addSong(song)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            view.isFavouriteSong(true)
                        })
    }

    private fun getCategory(song: Song) {
        compositeDisposable.add(
                categoryInteractor.getById(song.categoryId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::setSongCategory))
    }

    private fun fetchChords(song: Song) {
        compositeDisposable.add(
                chordInteractor.getChordsFromSong(song)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::setSongChords))
    }

}
