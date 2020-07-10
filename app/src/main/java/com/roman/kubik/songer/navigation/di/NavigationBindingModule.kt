package com.roman.kubik.songer.navigation.di

import com.roman.kubik.songer.home.navigation.HomeNavigator
import com.roman.kubik.songer.navigation.AppNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class NavigationBindingModule {

    @Binds
    abstract fun bindHomeNavigator(appNavigator: AppNavigator): HomeNavigator

}