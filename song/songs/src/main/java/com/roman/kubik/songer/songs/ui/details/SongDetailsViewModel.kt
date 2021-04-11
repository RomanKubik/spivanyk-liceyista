package com.roman.kubik.songer.songs.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.roman.kubik.settings.domain.repository.SettingsRepository
import com.roman.kubik.songer.chords.ChordsImageMapper
import com.roman.kubik.songer.chords.ChordsTransposer
import com.roman.kubik.songer.core.AppResult
import com.roman.kubik.songer.core.navigation.SearchNavigator
import com.roman.kubik.songer.core.ui.base.search.BaseSearchViewModel
import com.roman.kubik.songer.songs.domain.repository.SongRepository
import com.roman.kubik.songer.songs.domain.song.SongCategory
import com.roman.kubik.songer.songs.navigation.SongsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SongDetailsViewModel @Inject constructor(
        private val songRepository: SongRepository,
        private val settingsRepository: SettingsRepository,
        private val songsNavigator: SongsNavigator,
        searchNavigator: SearchNavigator
) : BaseSearchViewModel(searchNavigator) {

    companion object {
        const val ADD_TO_HISTORY_DELAY = 5000L
    }

    private val _song = MutableLiveData<SongDetailsViewState>()
    val song: LiveData<SongDetailsViewState> = _song

    fun loadSong(songId: String) {
        _song.value = LoadingState
        viewModelScope.launch(Dispatchers.IO) {
            val prefs = settingsRepository.getPreferences()
            val result = songRepository.getSongById(songId)
            if (result is AppResult.Success) {
                val song = result.data
                _song.postValue(SuccessState(song, ChordsImageMapper.getChords(song.lyrics).toList(), prefs))
                if (song.category != SongCategory.WEB) {
                    delay(ADD_TO_HISTORY_DELAY)
                    songRepository.addSongToLastPlayed(song)
                }
            } else {
                _song.postValue(ErrorState((result as AppResult.Error).throwable))
            }
        }
    }

    fun editSong() {
        (song.value as? SuccessState)?.song?.id?.let(songsNavigator::navigateToEditSong)
    }

    fun shareSong() {
        (song.value as? SuccessState)?.song?.let(songsNavigator::shareSong)
    }

    fun deleteSong() {
        (song.value as? SuccessState)?.let {
            viewModelScope.launch(Dispatchers.IO) {
                songRepository.removeSong(it.song)
                withContext(Dispatchers.Main) {
                    _song.value = SongDeleteSuccess
                    songsNavigator.navigateUp()
                }
            }
        }
    }

    fun likeDislikeSong() {
        (song.value as? SuccessState)?.let {
            viewModelScope.launch(Dispatchers.IO) {
                val updatedSong = it.copy(song = it.song.copy(isFavourite = !it.song.isFavourite))
                updateSong(updatedSong)
            }
        }
    }

    fun transpositionUp() {
        (song.value as? SuccessState)?.let {
            viewModelScope.launch(Dispatchers.IO) {
                val lyrics = ChordsTransposer.transposeUp(it.song.lyrics)
                val updatedSong = it.copy(song = it.song.copy(lyrics = lyrics), chords = ChordsImageMapper.getChords(lyrics).toList())
                updateSong(updatedSong)
            }
        }
    }

    fun transpositionDown() {
        (song.value as? SuccessState)?.let {
            viewModelScope.launch(Dispatchers.IO) {
                val lyrics = ChordsTransposer.transposeDown(it.song.lyrics)
                val updatedSong = it.copy(song = it.song.copy(lyrics = lyrics), chords = ChordsImageMapper.getChords(lyrics).toList())
                updateSong(updatedSong)
            }
        }
    }

    private suspend fun updateSong(successState: SuccessState) {
        songRepository.createOrUpdateSong(successState.song)
        _song.postValue(successState)
    }
}