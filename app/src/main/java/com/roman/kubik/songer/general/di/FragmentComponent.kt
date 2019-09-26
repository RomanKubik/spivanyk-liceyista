package com.roman.kubik.songer.general.di

import com.roman.kubik.songer.presentation.main.di.MainComponent
import com.roman.kubik.songer.presentation.main.di.MainModule
import dagger.Subcomponent

@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {
    fun mainComponent(mainModule: MainModule): MainComponent

}