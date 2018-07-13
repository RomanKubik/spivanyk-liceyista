package com.roman.kubik.songer.presentation.main.di

import com.roman.kubik.songer.domain.song.SongInteractor
import com.roman.kubik.songer.general.di.ActivityScope
import com.roman.kubik.songer.presentation.main.MainActivity
import com.roman.kubik.songer.presentation.main.MainContract
import com.roman.kubik.songer.presentation.main.MainPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class MainModule(private val activity: MainActivity) {

    @Provides
    @ActivityScope
    fun getView(): MainContract.View = activity

    @Provides
    @ActivityScope
    fun getPresenter(mainPresenter: MainPresenter): MainContract.Presenter = mainPresenter

}
