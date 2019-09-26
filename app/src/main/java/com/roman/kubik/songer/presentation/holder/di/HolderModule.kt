package com.roman.kubik.songer.presentation.holder.di

import com.roman.kubik.songer.general.di.ActivityScope
import com.roman.kubik.songer.presentation.holder.HolderActivity
import com.roman.kubik.songer.presentation.holder.HolderContract
import com.roman.kubik.songer.presentation.holder.HolderPresenter
import dagger.Module
import dagger.Provides

@Module
class HolderModule(private val holderActivity: HolderActivity) {

    @Provides
    @ActivityScope
    fun getView(): HolderContract.View = holderActivity

    @Provides
    @ActivityScope
    fun getPresenter(holderPresenter: HolderPresenter): HolderContract.Presenter = holderPresenter
}