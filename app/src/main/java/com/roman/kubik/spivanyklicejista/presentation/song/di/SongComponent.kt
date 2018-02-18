package com.roman.kubik.spivanyklicejista.presentation.song.di

import com.roman.kubik.spivanyklicejista.general.di.ActivityScope
import com.roman.kubik.spivanyklicejista.presentation.song.SongActivity

import dagger.Subcomponent

/**
 * Created by kubik on 1/20/18.
 */

@ActivityScope
@Subcomponent(modules = arrayOf(SongModule::class))
interface SongComponent {

    fun inject(songActivity: SongActivity)

}
