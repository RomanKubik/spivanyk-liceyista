package com.roman.kubik.songer.core.ui.base.search

import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.MaterialToolbar
import com.roman.kubik.core.R
import com.roman.kubik.songer.core.ui.base.BaseFragment

abstract class BaseSearchFragment : BaseFragment() {

    protected abstract val viewModel: BaseSearchViewModel

    override fun setupToolbar(toolbar: Toolbar?) {
        super.setupToolbar(toolbar)
        (toolbar as? MaterialToolbar)?.apply {
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