package com.roman.kubik.songer.songs.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.roman.kubik.settings.domain.repository.HintsConfigRepository
import com.roman.kubik.songer.chords.ChordsRecognizer
import com.roman.kubik.songer.core.AppResult
import com.roman.kubik.songer.core.Command
import com.roman.kubik.songer.core.ui.base.BaseViewModel
import com.roman.kubik.songer.songs.domain.repository.SongRepository
import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.domain.song.SongCategory
import com.roman.kubik.songer.songs.navigation.SongsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EditSongViewModel @Inject constructor(
        private val songRepository: SongRepository,
        private val hintsConfigRepository: HintsConfigRepository,
        private val songsNavigator: SongsNavigator
) : BaseViewModel() {

    private val _song = MutableLiveData<EditSongViewState>()
    val song: LiveData<EditSongViewState> = _song
    val showRecognizerHintCommand = Command<Unit>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val hints = hintsConfigRepository.getHintsConfig()
            if (!hints.chordsRecognizerShown) {
                showRecognizerHintCommand.postValue(Unit)
                hintsConfigRepository.updateHintsConfig(hints.copy(chordsRecognizerShown = true))
            }
        }
    }

    fun loadSong(songId: String?) {
        if (songId == null) {
            _song.value = NewSongState
            return
        }
        _song.value = LoadingState
        viewModelScope.launch(Dispatchers.IO) {
            val result = songRepository.getSongById(songId)
            if (result is AppResult.Success) {
                _song.postValue(EditSongState(result.data))
            } else {
                _song.postValue(ErrorState((result as AppResult.Error).throwable))
            }
        }
    }

    fun save(title: String, lyrics: String) {
        if (title.isBlank()) {
            _song.value = EmptyTitle
            return
        }
        if (lyrics.isBlank()) {
            _song.value = EmptyLyrics
            return
        }
        val song = updateSong(title, lyrics)
        _song.value = LoadingState
        viewModelScope.launch(Dispatchers.IO) {
            val result = songRepository.createOrUpdateSong(song)
            if (result is AppResult.Success) {
                withContext(Dispatchers.Main) {
                    songsNavigator.navigateUp()
                }
            } else {
                _song.postValue(ErrorState(Exception()))
            }

        }
    }

    fun recognize(title: String, lyrics: String) {
        _song.postValue(EditSongState(updateSong(title, ChordsRecognizer.recognizeChords(lyrics))))
    }

    private fun updateSong(title: String, lyrics: String): Song {
        val currentSong = (song.value as? EditSongState)?.song
        return currentSong?.copy(title = title, lyrics = lyrics)
                ?: Song(title, lyrics, SongCategory.MY_SONGS)
    }
}