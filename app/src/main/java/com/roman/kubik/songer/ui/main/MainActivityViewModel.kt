package com.roman.kubik.songer.ui.main

import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
import com.roman.kubik.songer.analytics.core.AnalyticsService
import com.roman.kubik.songer.analytics.events.RandomSongEvent
import com.roman.kubik.songer.core.ui.base.BaseViewModel
import com.roman.kubik.songer.navigation.MainNavigator
import com.roman.kubik.songer.room.database.DatabaseManager
import com.roman.kubik.songer.shaker.ShakeDetector
import com.roman.kubik.songer.songs.domain.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import javax.inject.Inject

@FlowPreview
@HiltViewModel
class MainActivityViewModel @Inject constructor(
        private val databaseManager: DatabaseManager,
        private val songRepository: SongRepository,
        private val shakeDetector: ShakeDetector,
        private val analyticsService: AnalyticsService,
        private val navigator: MainNavigator
) : BaseViewModel() {

    init {
        viewModelScope.launch {
            shakeDetector
                    .shakeDetected()
                    .debounce(250)
                    .collect {
                        analyticsService.log(RandomSongEvent(RandomSongEvent.Source.SHAKE))
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

    fun onSelectRandomSong() {
        analyticsService.log(RandomSongEvent(RandomSongEvent.Source.BOTTOM_MENU))
        navigateToRandomSong()
    }

    private fun navigateToRandomSong() {
        viewModelScope.launch(Dispatchers.IO) {
            val song = songRepository.getRandomSong()
            withContext(Dispatchers.Main) {
                navigator.navigateToSongDetails(song.id)
            }
        }
    }
}