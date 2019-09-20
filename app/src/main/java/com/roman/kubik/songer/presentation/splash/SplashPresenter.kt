package com.roman.kubik.songer.presentation.splash

import com.roman.kubik.songer.data.database.DatabaseManager
import com.roman.kubik.songer.domain.navigation.NavigationInteractor
import com.roman.kubik.songer.general.di.ActivityScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class SplashPresenter @Inject constructor(private val databaseManager: DatabaseManager,
                                          private val navigationInteractor: NavigationInteractor,
                                          private val cd: CompositeDisposable)
    : SplashContract.Presenter {

    override fun onCreate() {
        cd.add(
                databaseManager.createDatabase()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            navigationInteractor.toMainActivity()
                        }, {
                            cd.add(databaseManager.reset()
                                    .subscribeOn(Schedulers.io())
                                    .subscribe(navigationInteractor::restart))
                        })
        )
    }
}