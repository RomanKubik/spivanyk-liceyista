package com.roman.kubik.songer.presentation.holder.di

import com.roman.kubik.songer.general.di.ActivityScope
import com.roman.kubik.songer.presentation.holder.HolderActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [HolderModule::class])
interface HolderComponent {

    fun inject(holderActivity: HolderActivity)
}