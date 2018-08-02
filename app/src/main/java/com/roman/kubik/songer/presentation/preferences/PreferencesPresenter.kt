package com.roman.kubik.songer.presentation.preferences

import com.roman.kubik.songer.domain.chord.ChordInteractor
import com.roman.kubik.songer.general.di.ActivityScope
import javax.inject.Inject

/**
 * Created by kubik on 3/10/18.
 */
@ActivityScope
class PreferencesPresenter @Inject constructor(private val chordInteractor: ChordInteractor)
    : PreferencesContract.Presenter {

    override fun destroy() {
        chordInteractor.updateChordRepository()
    }


}
