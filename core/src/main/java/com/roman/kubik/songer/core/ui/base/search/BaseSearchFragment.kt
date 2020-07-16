package com.roman.kubik.songer.core.ui.base.search

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import com.roman.kubik.core.R
import com.roman.kubik.songer.core.ui.base.BaseFragment

abstract class BaseSearchFragment : BaseFragment() {

    protected abstract val viewModel: BaseSearchViewModel
    protected lateinit var searchMenuItem: MenuItem
    protected lateinit var searchView: SearchView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        searchMenuItem = menu.findItem(R.id.search)
        searchView = searchMenuItem.actionView as SearchView
        searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return if (query != null) {
                        viewModel.handleSearchQuery(query)
                        true
                    } else {
                        false
                    }
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return if (newText != null) {
                        viewModel.handleSearchQuery(newText)
                        true
                    } else {
                        false
                    }
                }

            })
        }
    }
}