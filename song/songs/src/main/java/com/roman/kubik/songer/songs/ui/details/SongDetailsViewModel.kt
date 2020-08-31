package com.roman.kubik.songer.songs.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.roman.kubik.settings.domain.preference.Preferences
import com.roman.kubik.settings.domain.repository.SettingsRepository
import com.roman.kubik.songer.core.navigation.SearchNavigator
import com.roman.kubik.songer.core.ui.base.search.BaseSearchViewModel
import com.roman.kubik.songer.chords.ChordsImageMapper
import com.roman.kubik.songer.chords.ChordsTransposer
import com.roman.kubik.songer.chords.model.Chord
import com.roman.kubik.songer.songs.domain.repository.SongRepository
import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.domain.song.SongCategory
import com.roman.kubik.songer.songs.navigation.SongsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SongDetailsViewModel @ViewModelInject constructor(
        private val songRepository: SongRepository,
        private val settingsRepository: SettingsRepository,
        private val songsNavigator: SongsNavigator,
        searchNavigator: SearchNavigator
) : BaseSearchViewModel(searchNavigator) {

    companion object {
        const val ADD_TO_HISTORY_DELAY = 5000L
    }

    private val _song = MutableLiveData<SongDetails>()
    private val _preferences = MutableLiveData<Preferences>()
    val song: LiveData<SongDetails> = _song
    val preferences: LiveData<Preferences> = _preferences

    fun loadSong(songId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _preferences.postValue(settingsRepository.getPreferences())
            val song = songRepository.getSongById(songId)
            val songDetails = SongDetails(song, ChordsImageMapper.getChords(song.lyrics).toList())
            _song.postValue(songDetails)
            if (song.category != SongCategory.WEB) {
                delay(ADD_TO_HISTORY_DELAY)
                songRepository.addSongToLastPlayed(song)
            }
        }
    }

    fun editSong() {
        song.value?.song?.id?.let(songsNavigator::navigateToEditSong)
    }

    fun shareSong() {
        song.value?.song?.let(songsNavigator::shareSong)
    }

    fun likeDislikeSong() {
        song.value?.let {
            viewModelScope.launch(Dispatchers.IO) {
                val updatedSong = it.copy(song = it.song.copy(isFavourite = !it.song.isFavourite))
                updateSong(updatedSong)
            }
        }
    }

    fun transpositionUp() {
        song.value?.let {
            viewModelScope.launch(Dispatchers.IO) {
                val updatedSong = it.copy(song = it.song.copy(lyrics = ChordsTransposer.transposeUp(it.song.lyrics)))
                updateSong(updatedSong)
            }
        }
    }

    fun transpositionDown() {
        song.value?.let {
            viewModelScope.launch(Dispatchers.IO) {
                val updatedSong = it.copy(song = it.song.copy(lyrics = ChordsTransposer.transposeDown(it.song.lyrics)))
                updateSong(updatedSong)
            }
        }
    }

    private suspend fun updateSong(songDetails: SongDetails) {
        songRepository.createOrUpdateSong(songDetails.song)
        _song.postValue(songDetails)
    }

    data class SongDetails(
            val song: Song,
            val chords: List<Chord>
    )
}