package com.roman.kubik.songer.general.di

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.Module

import javax.inject.Singleton

import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Main application module
 * Created by kubik on 1/14/18.
 */
@Module
class ApplicationModule(private val applicationContext: Context) {

    @Provides
    @Singleton
    fun getApplicationContext(): Context = applicationContext

    @Provides
    fun getCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @Singleton
    fun getFirebaseAnalytics(context: Context): FirebaseAnalytics = FirebaseAnalytics.getInstance(context)
}