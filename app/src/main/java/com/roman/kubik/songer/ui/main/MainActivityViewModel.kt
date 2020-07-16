package com.roman.kubik.songer.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.navigation.fragment.NavHostFragment
import com.roman.kubik.songer.core.ui.base.BaseViewModel
import com.roman.kubik.songer.navigation.MainNavigator
import com.roman.kubik.songer.room.database.DatabaseManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivityViewModel @ViewModelInject constructor(
        private val databaseManager: DatabaseManager,
        private val navigator: MainNavigator
) : BaseViewModel() {
    fun create(navHostFragment: NavHostFragment) {
        navigator.setNavHostFragment(navHostFragment)
        GlobalScope.launch(Dispatchers.IO) {
            databaseManager.createDatabase()
        }
    }
}