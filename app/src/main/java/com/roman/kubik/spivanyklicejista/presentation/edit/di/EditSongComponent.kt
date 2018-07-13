package com.roman.kubik.spivanyklicejista.presentation.edit.di

import com.roman.kubik.spivanyklicejista.general.di.ActivityScope
import com.roman.kubik.spivanyklicejista.presentation.edit.EditSongActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [EditSongModule::class])
interface EditSongComponent {

    fun inject(activity: EditSongActivity)
}