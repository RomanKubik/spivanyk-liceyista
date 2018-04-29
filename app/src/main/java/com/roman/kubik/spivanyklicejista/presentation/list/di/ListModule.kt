package com.roman.kubik.spivanyklicejista.presentation.list.di

import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor
import com.roman.kubik.spivanyklicejista.general.di.ActivityScope
import com.roman.kubik.spivanyklicejista.presentation.list.ListActivity
import com.roman.kubik.spivanyklicejista.presentation.list.ListContract
import com.roman.kubik.spivanyklicejista.presentation.list.ListPresenter

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * [ListActivity] module
 * Created by kubik on 1/14/18.
 */
@Module
class ListModule constructor(private val activity: ListActivity) {

    @Provides
    @ActivityScope
    fun getView(): ListContract.View = activity

    @Provides
    @ActivityScope
    fun getPresenter(listPresenter: ListPresenter): ListContract.Presenter = listPresenter

}
