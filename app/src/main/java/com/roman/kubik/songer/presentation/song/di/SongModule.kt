package com.roman.kubik.songer.presentation.song.di

import com.roman.kubik.songer.general.di.ActivityScope
import com.roman.kubik.songer.presentation.song.SongActivity
import com.roman.kubik.songer.presentation.song.SongContract
import com.roman.kubik.songer.presentation.song.SongPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by kubik on 1/20/18.
 */
@Module
class SongModule(private val activity: SongActivity) {

    @Provides
    @ActivityScope
    fun getView(): SongContract.View = activity

    @Provides
    @ActivityScope
    fun getPresenter(songPresenter: SongPresenter): SongContract.Presenter {
        return songPresenter
    }
}
