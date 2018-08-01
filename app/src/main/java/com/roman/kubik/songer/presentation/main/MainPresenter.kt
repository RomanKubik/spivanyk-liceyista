package com.roman.kubik.songer.presentation.main

import com.roman.kubik.songer.Constants
import com.roman.kubik.songer.domain.favourite.FavouriteInteractor
import com.roman.kubik.songer.domain.song.SongInteractor
import com.roman.kubik.songer.general.di.ActivityScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

@ActivityScope
class MainPresenter @Inject
constructor(private val view: MainContract.View,
            private val songInteractor: SongInteractor,
            private val favouriteInteractor: FavouriteInteractor,
            private val compositeDisposable: CompositeDisposable) : MainContract.Presenter {

    override fun requestData() {
        compositeDisposable.addAll(
                songInteractor.getCountByCategory(Constants.Category.PATRIOTIC_ID)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::setPatrioticsCount, view::showError),
                songInteractor.getCountByCategory(Constants.Category.BONFIRE_ID)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::setBonfiresCount, view::showError),
                songInteractor.getCountByCategory(Constants.Category.ABROAD_ID)
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

    override fun requestRandom() {
        compositeDisposable.add(
                songInteractor.randomSong
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::navigateToSong, view::showError))
    }

    override fun destroy() {
        compositeDisposable.clear()
    }

}