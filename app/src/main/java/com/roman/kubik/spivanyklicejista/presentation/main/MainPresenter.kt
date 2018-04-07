package com.roman.kubik.spivanyklicejista.presentation.main

import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor
import com.roman.kubik.spivanyklicejista.general.di.ActivityScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

@ActivityScope
class MainPresenter @Inject
constructor(private val view: MainContract.View, private val songInteractor: SongInteractor, private val compositeDisposable: CompositeDisposable) : MainContract.Presenter {

    private val random = Random()

    override fun requestData() {
        compositeDisposable.addAll(
                songInteractor.getCountByCategory(1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::setPatrioticsCount, view::showError),
                songInteractor.getCountByCategory(2)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::setBonfiresCount, view::showError),
                songInteractor.getCountByCategory(3)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::setAbroadsCount, view::showError),
                songInteractor.count
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::setAllCount, view::showError))
    }

    override fun requestRandom() {
        compositeDisposable.add(
                songInteractor.all
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ l -> view.navigateToSong(l[random.nextInt(l.size)]) }, view::showError))
    }

    override fun onDestroy() {
        compositeDisposable.clear()
    }


}