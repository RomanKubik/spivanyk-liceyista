package com.roman.kubik.songer.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import com.roman.kubik.songer.room.database.DatabaseManager
import com.roman.kubik.songer.core.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivityViewModel @ViewModelInject constructor(private val databaseManager: DatabaseManager) : BaseViewModel() {
    fun create() {
        GlobalScope.launch(Dispatchers.IO) {
            databaseManager.createDatabase()
        }
    }

}