package com.roman.kubik.songer.core.ui.base

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI

abstract class BaseFragment : Fragment() {

    protected open fun setupToolbar(toolbar: Toolbar?) {
        if (toolbar != null) {
            NavigationUI.setupWithNavController(toolbar, findNavController())
        }
    }
}