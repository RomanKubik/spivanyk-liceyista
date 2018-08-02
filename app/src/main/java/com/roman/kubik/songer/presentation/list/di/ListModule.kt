package com.roman.kubik.songer.presentation.list.di

import com.roman.kubik.songer.domain.song.SongInteractor
import com.roman.kubik.songer.general.di.ActivityScope
import com.roman.kubik.songer.presentation.list.ListActivity
import com.roman.kubik.songer.presentation.list.ListContract
import com.roman.kubik.songer.presentation.list.ListPresenter

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
