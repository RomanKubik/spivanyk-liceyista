package com.roman.kubik.songer.general.di

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
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun mainComponent(mainModule: MainModule): MainComponent
    fun listComponent(listModule: ListModule): ListComponent
    fun songComponent(songModule: SongModule): SongComponent
    fun preferencesComponent(preferencesModule: PreferencesModule): PreferencesComponent
    fun editSongComponent(editSongModule: EditSongModule): EditSongComponent
}