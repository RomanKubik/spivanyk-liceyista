package com.roman.kubik.songer.presentation.splash

import android.os.Bundle
import com.roman.kubik.songer.general.di.ActivityComponent
import com.roman.kubik.songer.presentation.BaseActivity
import com.roman.kubik.songer.presentation.splash.di.SplashModule
import javax.inject.Inject

/**
 * Launcher activity
 * Created by kubik on 1/14/18.
 */

class SplashActivity : BaseActivity(), SplashContract.View {

    @Inject
    lateinit var presenter: SplashContract.Presenter

    override fun injectActivity(activityComponent: ActivityComponent) {
        activityComponent.splashComponent(SplashModule(this)).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate()
    }
}
