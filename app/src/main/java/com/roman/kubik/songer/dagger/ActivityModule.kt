package com.roman.kubik.songer.dagger

import com.roman.kubik.songer.navigation.NavigationService
import com.roman.kubik.songer.navigation.NavigationServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Provides activity specific dependencies
 */
@Module
object ActivityModule {

    @Provides
    @JvmStatic
    fun provideNavigationService(navigationServiceImpl: NavigationServiceImpl): NavigationService = navigationServiceImpl
}