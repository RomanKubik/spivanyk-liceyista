package com.roman.kubik.spivanyklicejista.general.di

import android.content.Context
import dagger.Module

import javax.inject.Singleton

import dagger.Provides

/**
 * Main application module
 * Created by kubik on 1/14/18.
 */
@Module
class ApplicationModule(private val applicationContext: Context) {

    @Provides
    @Singleton
    fun getApplicationContext(): Context = applicationContext
}