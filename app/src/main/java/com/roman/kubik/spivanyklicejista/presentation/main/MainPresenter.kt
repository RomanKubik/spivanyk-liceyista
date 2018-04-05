package com.roman.kubik.spivanyklicejista.presentation.main

import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor
import com.roman.kubik.spivanyklicejista.general.di.ActivityScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class MainPresenter @Inject
constructor(private val view: MainContract.View, private val songInteractor: SongInteractor, private val compositeDisposable: CompositeDisposable) : MainContract.Presenter {
    override fun requestData() {
        compositeDisposable.addAll(
                songInteractor.getAllByCategory(1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map { l -> l.size }
                        .subscribe(view::setPatrioticsCount, view::showError),
                songInteractor.getAllByCategory(2)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map { l -> l.size }
                        .subscribe(view::setBonfiresCount, view::showError),
                songInteractor.getAllByCategory(3)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map { l -> l.size }
                        .subscribe(view::setAbroadsCount, view::showError),
                songInteractor.all
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map { l -> l.size }
                        .subscribe(view::setAllCount, view::showError))
    }

    override fun requestRandom() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}