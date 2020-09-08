package com.roman.kubik.songer.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
import com.roman.kubik.songer.core.ui.base.BaseViewModel
import com.roman.kubik.songer.navigation.MainNavigator
import com.roman.kubik.songer.room.database.DatabaseManager
import com.roman.kubik.songer.shaker.ShakeDetector
import com.roman.kubik.songer.songs.domain.repository.SongRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn

@FlowPreview
class MainActivityViewModel @ViewModelInject constructor(
        private val databaseManager: DatabaseManager,
        private val songRepository: SongRepository,
        private val shakeDetector: ShakeDetector,
        private val navigator: MainNavigator
) : BaseViewModel() {

    init {
        viewModelScope.launch {
            shakeDetector
                    .shakeDetected()
                    .debounce(250)
                    .collect {
                        navigateToRandomSong()
                    }
        }
    }

    fun create(navHostFragment: NavHostFragment) {
        navigator.setNavHostFragment(navHostFragment)
        GlobalScope.launch(Dispatchers.IO) {
            databaseManager.createDatabase()
        }
    }

    fun navigateToRandomSong() {
        viewModelScope.launch(Dispatchers.IO) {
            val song = songRepository.getRandomSong()
            withContext(Dispatchers.Main) {
                navigator.navigateToSongDetails(song.id)
            }
        }
    }
}