package com.roman.kubik.songer.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.roman.kubik.songer.activityComponent

abstract class BaseFragment: Fragment() {

    abstract val viewModel: BaseViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.navigationService = activityComponent.navigationService
    }
}