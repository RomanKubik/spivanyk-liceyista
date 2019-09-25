package com.roman.kubik.songer.presentation.splash.di

import com.roman.kubik.songer.general.di.ActivityScope
import com.roman.kubik.songer.presentation.splash.SplashActivity
import dagger.Subcomponent

/**
 * Created by kubik on 3/10/18.
 */

@ActivityScope
@Subcomponent(modules = [(SplashModule::class)])
interface SplashComponent {

    fun inject(splashActivity: SplashActivity)
}
