package com.roman.kubik.songer.core.ui.base.search

import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import com.roman.kubik.songer.core.R
import com.roman.kubik.songer.core.ui.base.BaseFragment

abstract class BaseSearchFragment<VB: ViewBinding> : BaseFragment<VB>() {

    protected abstract val viewModel: BaseSearchViewModel

    override fun setupToolbar(toolbar: Toolbar?) {
        super.setupToolbar(toolbar)
        toolbar?.apply {
            inflateMenu(R.menu.search_menu_static)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.search -> {
                        viewModel.openSearch()
                        true
                    }
                    else -> false
                }
            }
        }
    }
}