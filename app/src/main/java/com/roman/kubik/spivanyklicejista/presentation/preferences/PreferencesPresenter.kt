package com.roman.kubik.spivanyklicejista.presentation.preferences

import com.roman.kubik.spivanyklicejista.general.di.ActivityScope
import javax.inject.Inject

/**
 * Created by kubik on 3/10/18.
 */
@ActivityScope
class PreferencesPresenter @Inject constructor(private val activity: PreferencesContract.View) : PreferencesContract.Presenter
