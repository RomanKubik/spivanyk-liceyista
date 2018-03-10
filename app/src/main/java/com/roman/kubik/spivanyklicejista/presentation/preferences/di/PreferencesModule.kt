package com.roman.kubik.spivanyklicejista.presentation.preferences.di

import com.roman.kubik.spivanyklicejista.general.di.ActivityScope
import com.roman.kubik.spivanyklicejista.presentation.preferences.PreferencesActivity
import com.roman.kubik.spivanyklicejista.presentation.preferences.PreferencesContract
import com.roman.kubik.spivanyklicejista.presentation.preferences.PreferencesPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by kubik on 3/10/18.
 */
@Module
class PreferencesModule (private val activity: PreferencesActivity) {

    @Provides
    @ActivityScope
    fun getView(): PreferencesContract.View = activity

    @Provides
    @ActivityScope
    fun getPresenter(): PreferencesContract.Presenter = PreferencesPresenter(activity)
}