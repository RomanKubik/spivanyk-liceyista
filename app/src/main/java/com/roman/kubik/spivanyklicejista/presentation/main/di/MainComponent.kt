package com.roman.kubik.spivanyklicejista.presentation.main.di

import com.roman.kubik.spivanyklicejista.general.di.ActivityScope
import com.roman.kubik.spivanyklicejista.presentation.main.MainActivity

import dagger.Subcomponent

/**
 * [MainActivity] component
 * Created by kubik on 1/14/18.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {

    fun inject(mainActivity: MainActivity)
}
