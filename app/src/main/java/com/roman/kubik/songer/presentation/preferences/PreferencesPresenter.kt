package com.roman.kubik.songer.presentation.preferences

import android.util.Log
import com.roman.kubik.songer.data.database.DatabaseManager
import com.roman.kubik.songer.domain.chord.ChordInteractor
import com.roman.kubik.songer.general.di.ActivityScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by kubik on 3/10/18.
 */
@ActivityScope
class PreferencesPresenter @Inject constructor(private val chordInteractor: ChordInteractor,
                                               private val databaseManager: DatabaseManager)
    : PreferencesContract.Presenter {

    override fun destroy() {
        chordInteractor.updateChordRepository()
    }

    override fun reset() {
        val d = databaseManager.reset()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError{
                    Log.d("MyTag", "Migration failed")
                    it?.printStackTrace()
                }
                .subscribe {
                    Log.d("MyTag", "Migration success")
                }
    }
}
