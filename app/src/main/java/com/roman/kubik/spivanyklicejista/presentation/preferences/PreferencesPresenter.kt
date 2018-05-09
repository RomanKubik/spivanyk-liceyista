package com.roman.kubik.spivanyklicejista.presentation.preferences

import com.roman.kubik.spivanyklicejista.domain.preferences.PreferencesInteractor
import com.roman.kubik.spivanyklicejista.general.di.ActivityScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by kubik on 3/10/18.
 */
@ActivityScope
class PreferencesPresenter @Inject constructor(private val view: PreferencesContract.View,
                                               private val preferencesInteractor: PreferencesInteractor,
                                               private val compositeDisposable: CompositeDisposable) : PreferencesContract.Presenter {
    override fun start() {
        compositeDisposable.add(preferencesInteractor.preferenceChange()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                }, {}))
    }

    override fun destroy() {
        compositeDisposable.clear()
    }


}
