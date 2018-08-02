package com.roman.kubik.songer.general.di

import android.content.Context
import com.roman.kubik.songer.presentation.edit.di.EditSongComponent
import com.roman.kubik.songer.presentation.edit.di.EditSongModule

import com.roman.kubik.songer.presentation.list.di.ListComponent
import com.roman.kubik.songer.presentation.list.di.ListModule
import com.roman.kubik.songer.presentation.main.di.MainComponent
import com.roman.kubik.songer.presentation.main.di.MainModule
import com.roman.kubik.songer.presentation.preferences.di.PreferencesComponent
import com.roman.kubik.songer.presentation.preferences.di.PreferencesModule
import com.roman.kubik.songer.presentation.song.di.SongComponent
import com.roman.kubik.songer.presentation.song.di.SongModule

import javax.inject.Singleton

import dagger.Component

/**
 * Main application component
 * Created by kubik on 1/14/18.
 */

@Singleton
@Component(modules = [(ApplicationModule::class), (DatabaseModule::class), (InteractionModule::class), (GeneralModule::class)])
interface ApplicationComponent {
    fun getApplicationContext(): Context

    fun mainComponent(mainModule: MainModule): MainComponent
    fun listComponent(listModule: ListModule): ListComponent
    fun songComponent(songModule: SongModule): SongComponent
    fun preferencesComponent(preferencesModule: PreferencesModule): PreferencesComponent
    fun editSongComponent(editSongModule: EditSongModule): EditSongComponent

}
