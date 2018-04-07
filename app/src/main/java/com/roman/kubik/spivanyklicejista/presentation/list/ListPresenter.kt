package com.roman.kubik.spivanyklicejista.presentation.list

import com.annimon.stream.Collectors
import com.annimon.stream.Stream
import com.roman.kubik.spivanyklicejista.domain.song.Song
import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor
import com.roman.kubik.spivanyklicejista.general.di.ActivityScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Presenter for main activity
 * Created by kubik on 1/14/18.
 */

@ActivityScope
class ListPresenter @Inject
constructor(private val view: ListContract.View, private val songInteractor: SongInteractor, private val compositeDisposable: CompositeDisposable) : ListContract.Presenter {

    private var songs = mutableListOf<Song>()

    override fun fetchSongByCategory(categoryId: Int) {
        view.showProgress(true)
        when (categoryId) {
            -1 -> fetchAll()
            0 -> fetchLast()
            else -> fetchByCategoryId(categoryId)
        }

    }

    override fun filter(byTitle: String) {
        val title = byTitle.toLowerCase()
        view.onSongsFetched(Stream.of(songs)
                .filter({ s -> s.title.toLowerCase().contains(title) })
                .collect(Collectors.toList()))
    }

    private fun fetchAll() {
        compositeDisposable.add(songInteractor.all
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { view.showProgress(false) }
                .doOnSuccess({ s -> songs.addAll(s) })
                .subscribe(view::onSongsFetched
                ) { t -> view.showError(t.message) })
    }

    private fun fetchLast() {}

    private fun fetchByCategoryId(categoryId: Int) {
        compositeDisposable.add(songInteractor.getAllByCategory(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { view.showProgress(false) }
                .doOnSuccess({ s -> songs.addAll(s) })
                .subscribe(view::onSongsFetched
                ) { t -> view.showError(t.message) })
    }
}
