package com.roman.kubik.spivanyklicejista.presentation.edit.di

import com.roman.kubik.spivanyklicejista.general.di.ActivityScope
import com.roman.kubik.spivanyklicejista.presentation.edit.EditSongActivity
import com.roman.kubik.spivanyklicejista.presentation.edit.EditSongContract
import com.roman.kubik.spivanyklicejista.presentation.edit.EditSongPresenter
import dagger.Module
import dagger.Provides

@Module
class EditSongModule constructor(private val activity: EditSongActivity) {

    @Provides
    @ActivityScope
    fun getEditSongView(): EditSongContract.View = activity

    @Provides
    @ActivityScope
    fun getEditSongPresenter(presenter: EditSongPresenter): EditSongContract.Presenter = presenter
}