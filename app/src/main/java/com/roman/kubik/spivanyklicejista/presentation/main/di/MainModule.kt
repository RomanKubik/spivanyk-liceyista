package com.roman.kubik.spivanyklicejista.presentation.main.di

import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor
import com.roman.kubik.spivanyklicejista.general.di.ActivityScope
import com.roman.kubik.spivanyklicejista.presentation.main.MainActivity
import com.roman.kubik.spivanyklicejista.presentation.main.MainContract
import com.roman.kubik.spivanyklicejista.presentation.main.MainPresenter

import dagger.Module
import dagger.Provides

/**
 * [MainActivity] module
 * Created by kubik on 1/14/18.
 */
@Module
class MainModule constructor(private val activity: MainActivity) {

    @Provides
    @ActivityScope
    fun getView(): MainContract.View = activity

    @Provides
    @ActivityScope
    fun getPresenter(songInteractor: SongInteractor): MainContract.Presenter = MainPresenter(activity, songInteractor)

}
