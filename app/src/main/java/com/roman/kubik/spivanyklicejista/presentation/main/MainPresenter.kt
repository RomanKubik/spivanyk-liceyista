package com.roman.kubik.spivanyklicejista.presentation.main

import com.annimon.stream.Collectors
import com.annimon.stream.Stream
import com.roman.kubik.spivanyklicejista.domain.song.Song
import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor
import com.roman.kubik.spivanyklicejista.general.di.ActivityScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Presenter for main activity
 * Created by kubik on 1/14/18.
 */

@ActivityScope
class MainPresenter @Inject
constructor(private val view: MainContract.View, private val songInteractor: SongInteractor) : MainContract.Presenter {

    private var songs = mutableListOf<Song>()

    override fun fetchAllSongs() {
        view.showProgress(true)
        songInteractor.all
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { view.showProgress(false) }
                .doOnSuccess({ s -> songs.addAll(s) })
                .subscribe(view::onSongsFetched
                ) { t -> view.showError(t.message) }
    }

    override fun filter(byTitle: String) {
        val title = byTitle.toLowerCase()
        view.onSongsFetched(Stream.of(songs)
                .filter({ s -> s.title.toLowerCase().contains(title) })
                .collect(Collectors.toList()))
    }
}
