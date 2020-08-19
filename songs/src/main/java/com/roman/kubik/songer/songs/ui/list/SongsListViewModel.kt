package com.roman.kubik.songer.songs.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.roman.kubik.settings.domain.preference.Preferences
import com.roman.kubik.settings.domain.repository.SettingsRepository
import com.roman.kubik.songer.core.navigation.SearchNavigator
import com.roman.kubik.songer.core.ui.base.search.BaseSearchViewModel
import com.roman.kubik.songer.songs.domain.repository.SongRepository
import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.domain.song.SongCategory
import com.roman.kubik.songer.songs.navigation.SongsNavigator
import com.roman.kubik.songer.songs.ui.details.SongDetailsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SongsListViewModel @ViewModelInject constructor(
        private val songRepository: SongRepository,
        private val settingsRepository: SettingsRepository,
        private val songsNavigator: SongsNavigator,
        searchNavigator: SearchNavigator
) : BaseSearchViewModel(searchNavigator) {

    private val _songs = MutableLiveData<List<Song>>(emptyList())
    private val _preferences = MutableLiveData<Preferences>()
    val songs: LiveData<List<Song>> = _songs
    val preferences: LiveData<Preferences> = _preferences

    fun loadSongs(categoryId: String?) {
        GlobalScope.launch(Dispatchers.IO) {
            _preferences.postValue(settingsRepository.getPreferences())
            val result = if (categoryId == null) {
                songRepository.getAllSongs()
            } else {
                songRepository.getAllSongs(SongCategory.valueOf(categoryId))
            }
            _songs.postValue(result)
        }
    }

    fun searchSongs(query: String) {
        GlobalScope.launch(Dispatchers.IO) {
            _songs.postValue(songRepository.searchSong(query))
        }
    }

    fun selectSong(song: Song) {
        songsNavigator.navigateToSongDetails(song.id)
    }

}