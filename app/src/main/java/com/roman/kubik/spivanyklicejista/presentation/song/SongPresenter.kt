package com.roman.kubik.spivanyklicejista.presentation.song

import com.roman.kubik.spivanyklicejista.domain.category.CategoryInteractor
import com.roman.kubik.spivanyklicejista.domain.chord.ChordInteractor
import com.roman.kubik.spivanyklicejista.domain.favourite.FavouriteInteractor
import com.roman.kubik.spivanyklicejista.domain.song.Song
import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by kubik on 1/20/18.
 */

class SongPresenter @Inject
constructor(private val view: SongContract.View, private val songInteractor: SongInteractor,
            private val favouriteInteractor: FavouriteInteractor,
            private val categoryInteractor: CategoryInteractor,
            private val chordInteractor: ChordInteractor) : SongContract.Presenter {

    private lateinit var song: Song

    override fun fetchSong(id: Int) {
        songInteractor.getById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess({ s -> song = s })
                .doOnSuccess(this::isFavouriteSong)
                .doOnSuccess(this::getCategory)
                .doOnSuccess(this::fetchChords)
                .subscribe({ s -> view.showSong(s) }
                ) { t -> view.showError(t.message!!) }
    }

    override fun addToFavourite() {
        favouriteInteractor.isInFavouriteList(song)
                .subscribeOn(Schedulers.io())
                .subscribe({ b ->
                    if (b) removeSongFromFavourite(song)
                    else addSongToFavourite(song)
                })
    }

    private fun isFavouriteSong(song: Song) {
        favouriteInteractor.isInFavouriteList(song)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::isFavouriteSong, { t -> view.showError(t.message!!) })
    }

    private fun removeSongFromFavourite(song: Song) {
        favouriteInteractor.removeSong(song)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.isFavouriteSong(false)
                }
    }

    private fun addSongToFavourite(song: Song) {
        favouriteInteractor.addSong(song)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.isFavouriteSong(true)
                }
    }

    private fun getCategory(song: Song) {
        categoryInteractor.getById(song.categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::showCategory)
    }

    private fun fetchChords(song: Song) {
        chordInteractor.getChordsFromSong(song)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::showChords)
    }

}
