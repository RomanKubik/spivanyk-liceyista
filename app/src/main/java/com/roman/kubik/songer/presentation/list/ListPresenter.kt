package com.roman.kubik.songer.presentation.list

import com.roman.kubik.songer.domain.category.Category
import com.roman.kubik.songer.domain.navigation.NavigationInteractor
import com.roman.kubik.songer.domain.preferences.Preferences
import com.roman.kubik.songer.domain.preferences.PreferencesInteractor
import com.roman.kubik.songer.domain.song.Song
import com.roman.kubik.songer.domain.song.SongInteractor
import com.roman.kubik.songer.general.di.ActivityScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * Presenter for main activity
 * Created by kubik on 1/14/18.
 */

@ActivityScope
class ListPresenter @Inject
constructor(private val view: ListContract.View,
            private val navigationInteractor: NavigationInteractor,
            private val songInteractor: SongInteractor,
            private val preferencesInteractor: PreferencesInteractor,
            private val compositeDisposable: CompositeDisposable) : ListContract.Presenter {

    private var songs: List<Song> = ArrayList()
    private var categoryId = Category.ALL_ID

    override fun showSong(song: Song) {
        navigationInteractor.toSongActivity(song)
    }

    override fun deleteSong(song: Song) {
        compositeDisposable.add(songInteractor
                .deleteSong(song)
                .andThen(songInteractor.getAllByCategory(categoryId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess { view.onSongRemoved(song) }
                .subscribe(this::onSongsFetched) { view.showError(it.message) })
    }

    override fun undoDeletion() {
        compositeDisposable.add(songInteractor
                .undoDeletion()
                .andThen(songInteractor.getAllByCategory(categoryId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSongsFetched) { view.showError(it.message) })
    }

    override fun fetchPreferences() {
        compositeDisposable.add(
                preferencesInteractor
                        .preferences
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess { if (showDelete(it)) view.showDeletionTutorialDialog() }
                        .map { it.isChordsVisible }
                        .subscribe(view::onPreferencesFetched
                        ) { t -> view.showError(t.message) })
    }

    private fun showDelete(preferences: Preferences)
            = !preferences.tutorialPreferences.isDeleteShown && categoryId != Category.WEB_ID

    override fun fetchSongByCategory(categoryId: Int) {
        this.categoryId = categoryId
        if (categoryId == Category.WEB_ID && songs.isEmpty()) {
            view.showInfo(ListContract.InfoState.WEB_PLACEHOLDER)
            return
        }
        view.showProgress(true)
        compositeDisposable.add(
                songInteractor.getAllByCategory(categoryId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally { view.showProgress(false) }
                        .subscribe(this::onSongsFetched
                        ) { t -> view.showError(t.message) })
    }

    override fun filter(query: String) {
        if (categoryId == Category.WEB_ID && query.isEmpty()) {
            onSongsFetched(emptyList())
            view.showInfo(ListContract.InfoState.WEB_PLACEHOLDER)
            return
        }
        compositeDisposable.add(
                songInteractor.search(query, categoryId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::onSongsFetched
                        ) { t -> view.showError(t.message) })
    }

    override fun destroy() {
        compositeDisposable.clear()
    }

    override fun onTutorialDialogShowed() {
        compositeDisposable.add(
                preferencesInteractor.setDeleteTutorialShown()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe())
    }

    private fun onSongsFetched(songs: List<Song>) {
        if (songs != this.songs) {
            this.songs = songs
            view.onSongsFetched(songs)
            if (songs.isNotEmpty()) {
                view.showInfo(ListContract.InfoState.OK)
            } else {
                view.showInfo(ListContract.InfoState.NOT_FOUND)
            }
        }
    }
}
