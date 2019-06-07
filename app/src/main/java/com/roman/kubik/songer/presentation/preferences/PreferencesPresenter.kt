package com.roman.kubik.songer.presentation.preferences

import android.annotation.SuppressLint
import com.roman.kubik.songer.data.database.DatabaseManager
import com.roman.kubik.songer.domain.chord.ChordInteractor
import com.roman.kubik.songer.domain.navigation.NavigationInteractor
import com.roman.kubik.songer.general.di.ActivityScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by kubik on 3/10/18.
 */
@ActivityScope
class PreferencesPresenter @Inject constructor(private val view: PreferencesContract.View,
                                               private val chordInteractor: ChordInteractor,
                                               private val navigationInteractor: NavigationInteractor,
                                               private val databaseManager: DatabaseManager)
    : PreferencesContract.Presenter {

    override fun destroy() {
        chordInteractor.updateChordRepository()
    }

    @SuppressLint("CheckResult")
    override fun reset() {
        databaseManager.reset()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError{
                    view.showResetError()
                }
                .subscribe(navigationInteractor::restart)
    }

    override fun signIn() {
        navigationInteractor.toSignIn(PreferencesContract.CODE_SIGN_IN)
    }
}
