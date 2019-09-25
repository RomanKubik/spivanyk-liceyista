package com.roman.kubik.songer.general.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson
import com.roman.kubik.songer.data.shaker.ShakeDetectorImpl
import com.roman.kubik.songer.domain.shaker.ShakeDetector
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

    @Provides
    @Singleton
    fun getShackerDetector(context: Context): ShakeDetector = ShakeDetectorImpl(context)

    @Provides
    @Singleton
    fun getSharedPreferences(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    @Provides
    @Singleton
    fun getGson() = Gson()
}