package com.roman.kubik.songer.general.di

import android.content.Context

import javax.inject.Singleton

import dagger.Component

/**
 * Main application component
 * Created by kubik on 1/14/18.
 */

@Singleton
@Component(modules = [(ApplicationModule::class), (DatabaseModule::class), (InteractionModule::class), (GeneralModule::class), (RepositoryModule::class)])
interface ApplicationComponent {
    fun getApplicationContext(): Context
    fun getActivityComponent(module: ActivityModule): ActivityComponent
}
