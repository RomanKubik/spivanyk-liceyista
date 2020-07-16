package com.roman.kubik.songer.core.ui.base

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI

abstract class BaseFragment : Fragment() {

    protected fun setupToolbar(toolbar: Toolbar?) {
        if (toolbar != null) {
            (activity as? BaseActivity)?.setSupportActionBar(toolbar)
            NavigationUI.setupWithNavController(toolbar, findNavController())
        }
    }
}