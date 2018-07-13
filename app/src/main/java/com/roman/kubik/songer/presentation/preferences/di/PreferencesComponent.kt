package com.roman.kubik.songer.presentation.preferences.di

import com.roman.kubik.songer.general.di.ActivityScope
import com.roman.kubik.songer.presentation.preferences.PreferencesActivity
import dagger.Subcomponent

/**
 * Created by kubik on 3/10/18.
 */

@ActivityScope
@Subcomponent(modules = [(PreferencesModule::class)])
interface PreferencesComponent {

    fun inject(preferencesActivity: PreferencesActivity)
}
