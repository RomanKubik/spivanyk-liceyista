package com.roman.kubik.spivanyklicejista.presentation.main.di

import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor
import com.roman.kubik.spivanyklicejista.general.di.ActivityScope
import com.roman.kubik.spivanyklicejista.presentation.main.MainActivity
import com.roman.kubik.spivanyklicejista.presentation.main.MainContract
import com.roman.kubik.spivanyklicejista.presentation.main.MainPresenter
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
    fun getPresenter(songInteractor: SongInteractor, compositeDisposable: CompositeDisposable): MainContract.Presenter = MainPresenter(activity, songInteractor, compositeDisposable)

}
