package com.roman.kubik.songer.presentation.main.di

import com.roman.kubik.songer.general.di.ActivityScope
import com.roman.kubik.songer.presentation.main.MainFragment
import com.roman.kubik.songer.presentation.main.MainContract
import com.roman.kubik.songer.presentation.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val fragment: MainFragment) {

    @Provides
    @ActivityScope
    fun getView(): MainContract.View = fragment

    @Provides
    @ActivityScope
    fun getPresenter(mainPresenter: MainPresenter): MainContract.Presenter = mainPresenter

}
