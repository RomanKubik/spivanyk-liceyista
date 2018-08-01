package com.roman.kubik.songer.presentation.edit.di

import com.roman.kubik.songer.general.di.ActivityScope
import com.roman.kubik.songer.presentation.edit.EditSongActivity
import com.roman.kubik.songer.presentation.edit.EditSongContract
import com.roman.kubik.songer.presentation.edit.EditSongPresenter
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