package com.roman.kubik.songer.presentation.list.di

import com.roman.kubik.songer.general.di.ActivityScope
import com.roman.kubik.songer.presentation.list.ListActivity

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
