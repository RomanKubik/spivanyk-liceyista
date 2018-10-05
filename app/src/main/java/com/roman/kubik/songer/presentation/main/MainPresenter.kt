package com.roman.kubik.songer.presentation.main

import com.roman.kubik.songer.domain.category.Category
import com.roman.kubik.songer.domain.favourite.FavouriteInteractor
import com.roman.kubik.songer.domain.navigation.NavigationInteractor
import com.roman.kubik.songer.domain.song.SongInteractor
import com.roman.kubik.songer.general.di.ActivityScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class MainPresenter @Inject
constructor(private val view: MainContract.View,
            private val navigationInteractor: NavigationInteractor,
            private val songInteractor: SongInteractor,
            private val favouriteInteractor: FavouriteInteractor,
            private val compositeDisposable: CompositeDisposable) : MainContract.Presenter {

    override fun requestData() {
        compositeDisposable.addAll(
                songInteractor.getCountByCategory(Category.PATRIOTIC_ID)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::setPatrioticsCount, view::showError),
                songInteractor.getCountByCategory(Category.BONFIRE_ID)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::setBonfiresCount, view::showError),
                songInteractor.getCountByCategory(Category.ABROAD_ID)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::setAbroadsCount, view::showError),
                songInteractor.count
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::setAllCount, view::showError),
                favouriteInteractor.count
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::setFavouriteCount, view::showError))
    }

    override fun requestRandom() = navigationInteractor.toRandomSong()

    override fun selectCategory(categoryId: Int) = navigationInteractor.toListActivity(categoryId)

    override fun addSong() = navigationInteractor.toAddSongActivity()

    override fun showSettings() = navigationInteractor.toPreferencesActivity()

    override fun destroy() = compositeDisposable.clear()

}