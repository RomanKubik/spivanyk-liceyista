package com.roman.kubik.spivanyklicejista.presentation.song.di

import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor
import com.roman.kubik.spivanyklicejista.general.di.ActivityScope
import com.roman.kubik.spivanyklicejista.presentation.song.SongActivity
import com.roman.kubik.spivanyklicejista.presentation.song.SongContract
import com.roman.kubik.spivanyklicejista.presentation.song.SongPresenter

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
    fun getPresenter(songInteractor: SongInteractor): SongContract.Presenter {
        return SongPresenter(activity, songInteractor)
    }
}
