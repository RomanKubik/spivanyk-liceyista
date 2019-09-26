package com.roman.kubik.songer.presentation.main.di

import com.roman.kubik.songer.general.di.ActivityScope
import com.roman.kubik.songer.presentation.main.MainFragment
import dagger.Subcomponent

/**
 * [MainFragment] component
 * Created by kubik on 1/14/18.
 */
@ActivityScope
@Subcomponent(modules = [(MainModule::class)])
interface MainComponent {

    fun inject(mainFragment: MainFragment)
}