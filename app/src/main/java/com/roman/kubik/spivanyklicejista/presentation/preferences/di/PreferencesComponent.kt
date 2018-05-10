package com.roman.kubik.spivanyklicejista.presentation.preferences.di

import com.roman.kubik.spivanyklicejista.general.di.ActivityScope
import com.roman.kubik.spivanyklicejista.presentation.preferences.PreferencesActivity
import dagger.Subcomponent

/**
 * Created by kubik on 3/10/18.
 */

@ActivityScope
@Subcomponent(modules = [(PreferencesModule::class)])
interface PreferencesComponent {

    fun inject(preferencesActivity: PreferencesActivity)
}
