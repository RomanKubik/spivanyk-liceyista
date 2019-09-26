package com.roman.kubik.songer.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import com.roman.kubik.songer.general.android.SpivanykApplication
import com.roman.kubik.songer.general.android.SpivanykApplication.Companion.component
import com.roman.kubik.songer.general.di.FragmentComponent
import com.roman.kubik.songer.general.di.FragmentModule

abstract class BaseFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ButterKnife.bind(this, view)
    }

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        injectFragment(component.getFragmentComponent(FragmentModule(this)))
        super.onActivityCreated(savedInstanceState)
    }

    abstract fun injectFragment(fragmentComponent: FragmentComponent)

    protected fun getBaseActivity(): BaseActivity {
        return activity as BaseActivity
    }

    protected fun getApplication(): SpivanykApplication {
        return getBaseActivity().application as SpivanykApplication
    }
}