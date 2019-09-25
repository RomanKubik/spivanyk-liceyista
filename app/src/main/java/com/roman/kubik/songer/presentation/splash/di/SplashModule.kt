package com.roman.kubik.songer.presentation.splash.di

import com.roman.kubik.songer.general.di.ActivityScope
import com.roman.kubik.songer.presentation.splash.SplashActivity
import com.roman.kubik.songer.presentation.splash.SplashContract
import com.roman.kubik.songer.presentation.splash.SplashPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by kubik on 3/10/18.
 */
@Module
class SplashModule (private val activity: SplashActivity) {

    @Provides
    @ActivityScope
    fun getView(): SplashContract.View = activity

    @Provides
    @ActivityScope
    fun getPresenter(presenter: SplashPresenter): SplashContract.Presenter = presenter
}