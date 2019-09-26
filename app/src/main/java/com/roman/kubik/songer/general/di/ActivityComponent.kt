package com.roman.kubik.songer.general.di

import com.roman.kubik.songer.presentation.edit.di.EditSongComponent
import com.roman.kubik.songer.presentation.edit.di.EditSongModule
import com.roman.kubik.songer.presentation.holder.di.HolderComponent
import com.roman.kubik.songer.presentation.holder.di.HolderModule
import com.roman.kubik.songer.presentation.list.di.ListComponent
import com.roman.kubik.songer.presentation.list.di.ListModule
import com.roman.kubik.songer.presentation.main.di.MainComponent
import com.roman.kubik.songer.presentation.main.di.MainModule
import com.roman.kubik.songer.presentation.preferences.di.PreferencesComponent
import com.roman.kubik.songer.presentation.preferences.di.PreferencesModule
import com.roman.kubik.songer.presentation.song.di.SongComponent
import com.roman.kubik.songer.presentation.song.di.SongModule
import com.roman.kubik.songer.presentation.splash.di.SplashComponent
import com.roman.kubik.songer.presentation.splash.di.SplashModule
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun holderComponent(holderModule: HolderModule): HolderComponent
    fun listComponent(listModule: ListModule): ListComponent
    fun songComponent(songModule: SongModule): SongComponent
    fun preferencesComponent(preferencesModule: PreferencesModule): PreferencesComponent
    fun editSongComponent(editSongModule: EditSongModule): EditSongComponent
    fun splashComponent(splashModule: SplashModule): SplashComponent
}