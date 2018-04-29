package com.roman.kubik.spivanyklicejista.general.di

import android.content.Context
import com.roman.kubik.spivanyklicejista.presentation.edit.di.EditSongComponent
import com.roman.kubik.spivanyklicejista.presentation.edit.di.EditSongModule

import com.roman.kubik.spivanyklicejista.presentation.list.di.ListComponent
import com.roman.kubik.spivanyklicejista.presentation.list.di.ListModule
import com.roman.kubik.spivanyklicejista.presentation.main.di.MainComponent
import com.roman.kubik.spivanyklicejista.presentation.main.di.MainModule
import com.roman.kubik.spivanyklicejista.presentation.song.di.SongComponent
import com.roman.kubik.spivanyklicejista.presentation.song.di.SongModule

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
    fun editSongComponent(editSongModule: EditSongModule): EditSongComponent

}
