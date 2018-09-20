package com.roman.kubik.songer.presentation.list

import android.text.TextUtils
import com.annimon.stream.Collectors
import com.annimon.stream.Stream
import com.roman.kubik.songer.domain.category.Category
import com.roman.kubik.songer.domain.preferences.PreferencesInteractor
import com.roman.kubik.songer.domain.song.Song
import com.roman.kubik.songer.domain.song.SongInteractor
import com.roman.kubik.songer.general.di.ActivityScope
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
constructor(private val view: ListContract.View,
            private val songInteractor: SongInteractor,
            private val preferencesInteractor: PreferencesInteractor,
            private val compositeDisposable: CompositeDisposable) : ListContract.Presenter {

    private var songs = mutableListOf<Song>()
    private var categoryId = Category.ALL_ID

    override fun fetchPreferences() {
        compositeDisposable.add(
                preferencesInteractor
                        .isChordsVisible
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::onPreferencesFetched
                        ) { t -> view.showError(t.message) })
    }

    override fun fetchSongByCategory(categoryId: Int) {
        this.categoryId = categoryId
        view.showProgress(true)
        compositeDisposable.add(
                songInteractor.getAllByCategory(categoryId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally { view.showProgress(false) }
                        .doOnSuccess { s -> songs.addAll(s) }
                        .subscribe(view::onSongsFetched
                        ) { t -> view.showError(t.message) })

    }

    override fun filter(query: String) {
        if (TextUtils.isEmpty(query)) return
        compositeDisposable.add(
                songInteractor.search(query, categoryId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::onSongsFetched
                        ) { t -> view.showError(t.message) })
    }

    override fun destroy() {
        compositeDisposable.clear()
    }
}
