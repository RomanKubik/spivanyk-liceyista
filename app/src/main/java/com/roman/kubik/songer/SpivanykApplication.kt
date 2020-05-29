package com.roman.kubik.songer

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.roman.kubik.songer.dagger.ComponentProvider
import com.roman.kubik.songer.dagger.DaggerActivityComponent
import com.roman.kubik.songer.dagger.DaggerApplicationComponent

class SpivanykApplication: Application(), ComponentProvider {

    override val component by lazy {
        DaggerApplicationComponent
                .factory()
                .create(this)
    }
}

val FragmentActivity.component get() = (application as ComponentProvider).component
val FragmentActivity.activityComponent get() = DaggerActivityComponent.factory().create(component, this)
val Fragment.component get() = requireActivity().component
val Fragment.activityComponent get() = requireActivity().activityComponent