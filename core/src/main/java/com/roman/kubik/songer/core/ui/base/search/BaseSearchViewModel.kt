package com.roman.kubik.songer.core.ui.base.search

import com.roman.kubik.songer.core.navigation.SearchNavigator
import com.roman.kubik.songer.core.ui.base.BaseViewModel

abstract class BaseSearchViewModel constructor(protected val searchNavigator: SearchNavigator) : BaseViewModel() {

    fun openSearch() {
        searchNavigator.navigateToSearch()
    }
}