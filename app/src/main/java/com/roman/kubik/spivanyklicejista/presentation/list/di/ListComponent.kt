package com.roman.kubik.spivanyklicejista.presentation.list.di

import com.roman.kubik.spivanyklicejista.general.di.ActivityScope
import com.roman.kubik.spivanyklicejista.presentation.list.ListActivity

import dagger.Subcomponent

/**
 * [ListActivity] component
 * Created by kubik on 1/14/18.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(ListModule::class))
interface ListComponent {

    fun inject(listActivity: ListActivity)
}
