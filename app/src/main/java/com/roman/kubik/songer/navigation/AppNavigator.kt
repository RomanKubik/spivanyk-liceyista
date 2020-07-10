package com.roman.kubik.songer.navigation

import androidx.navigation.NavController
import com.roman.kubik.songer.R
import com.roman.kubik.songer.home.navigation.HomeNavigator
import com.roman.kubik.songer.home.ui.HomeCategory
import javax.inject.Inject

class AppNavigator @Inject constructor(private val navController: NavController): HomeNavigator {

    override fun navigateToCategory(category: HomeCategory) {
        navController.navigate(R.id.menu_discover)
    }
}