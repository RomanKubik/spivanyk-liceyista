package com.roman.kubik.songer.navigation.di

import com.roman.kubik.songer.core.navigation.SearchNavigator
import com.roman.kubik.songer.home.navigation.HomeNavigator
import com.roman.kubik.songer.navigation.AppNavigator
import com.roman.kubik.songer.navigation.MainNavigator
import com.roman.kubik.songer.settings.presentation.navigation.SettingsNavigator
import com.roman.kubik.songer.songs.navigation.SongsNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationBindingModule {

    @Binds
    abstract fun bindMainNavigator(appNavigator: AppNavigator): MainNavigator

    @Binds
    abstract fun bindSearchNavigator(appNavigator: AppNavigator): SearchNavigator

    @Binds
    abstract fun bindHomeNavigator(appNavigator: AppNavigator): HomeNavigator

    @Binds
    abstract fun bindSongsNavigator(appNavigator: AppNavigator): SongsNavigator

    @Binds
    abstract fun bindSettingsNavigator(appNavigator: AppNavigator): SettingsNavigator
}