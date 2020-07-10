package com.roman.kubik.songer.home.navigation

import com.roman.kubik.songer.home.ui.HomeCategory

interface HomeNavigator {

    fun navigateToCategory(category: HomeCategory)

}