package com.roman.kubik.songer.general.di

import android.app.Activity
import com.roman.kubik.songer.domain.navigation.NavigationInteractor
import com.roman.kubik.songer.domain.navigation.NavigationService
import com.roman.kubik.songer.navigation.NavigationServiceImpl
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    fun activity(): Activity {
        return activity
    }

    @Provides
    fun navigationService(activity: Activity) = NavigationServiceImpl(activity)

    @Provides
    fun navigationInteractor(navigationService: NavigationService) = NavigationInteractor(navigationService)
}