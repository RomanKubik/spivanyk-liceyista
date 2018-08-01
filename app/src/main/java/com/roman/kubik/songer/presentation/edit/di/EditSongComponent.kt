package com.roman.kubik.songer.presentation.edit.di

import com.roman.kubik.songer.general.di.ActivityScope
import com.roman.kubik.songer.presentation.edit.EditSongActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [EditSongModule::class])
interface EditSongComponent {

    fun inject(activity: EditSongActivity)
}