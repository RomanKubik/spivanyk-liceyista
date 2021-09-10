package com.roman.kubik.songer.songs.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.roman.kubik.settings.domain.preference.Preferences
import com.roman.kubik.settings.domain.repository.SettingsRepository
import com.roman.kubik.songer.core.AppResult
import com.roman.kubik.songer.core.ui.base.BaseViewModel
import com.roman.kubik.songer.songs.domain.repository.SongRepository
import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.domain.song.SongCategory
import com.roman.kubik.songer.songs.navigation.SongsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongsListViewModel @Inject constructor(
        private val songRepository: SongRepository,
        private val settingsRepository: SettingsRepository,
        private val songsNavigator: SongsNavigator
) : BaseViewModel() {

    private var searchJob: Job? = null
    private var preferences: Preferences? = null

    private val _songs = MutableLiveData<SongsListViewState>()
    val songs: LiveData<SongsListViewState> = _songs

    fun loadSongs(categoryId: String?) {
        _songs.value = LoadingState
        viewModelScope.launch(Dispatchers.IO) {
            val result = if (categoryId == null) {
                songRepository.getAllSongs()
            } else {
                songRepository.getAllSongs(SongCategory.valueOf(categoryId))
            }
            handleSongResult(result, getPreferences())
        }
    }

    fun searchSongs(query: String) {
        _songs.value = LoadingState
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            songRepository.searchSongFlow(query)
                    .flowOn(Dispatchers.IO)
                    .collect {
                        handleSongResult(it, getPreferences())
                    }
        }
    }

    fun selectSong(song: Song) {
        songsNavigator.navigateToSongDetails(song.id)
    }

    private suspend fun getPreferences(): Preferences {
        if (preferences == null) {
            preferences = settingsRepository.getPreferences()
        }
        return preferences!!
    }

    private fun handleSongResult(result: AppResult<List<Song>>, preferences: Preferences) {
        if (result is AppResult.Success) {
            if (result.data.isEmpty()) {
                _songs.postValue(NoSongsViewState)
            } else {
                _songs.postValue(SuccessState(result.data, preferences))
            }
        } else {
            _songs.postValue(ErrorState((result as AppResult.Error).throwable))
        }
    }

}