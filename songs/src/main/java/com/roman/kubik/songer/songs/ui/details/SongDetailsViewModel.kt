package com.roman.kubik.songer.songs.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.roman.kubik.songer.core.navigation.SearchNavigator
import com.roman.kubik.songer.core.ui.base.search.BaseSearchViewModel
import com.roman.kubik.songer.chords.ChordsImageMapper
import com.roman.kubik.songer.chords.model.Chord
import com.roman.kubik.songer.songs.domain.repository.SongRepository
import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.navigation.SongsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SongDetailsViewModel @ViewModelInject constructor(
        private val songRepository: SongRepository,
        private val songsNavigator: SongsNavigator,
        searchNavigator: SearchNavigator
) : BaseSearchViewModel(searchNavigator) {

    companion object {
        const val ADD_TO_HISTORY_DELAY = 5000L
    }

    private val _song = MutableLiveData<Song>()
    private val _chords = MutableLiveData<List<Chord>>()
    val song: LiveData<Song> = _song
    val chords: LiveData<List<Chord>> = _chords

    fun loadSong(songId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val song = songRepository.getSongById(songId)
            _chords.postValue(ChordsImageMapper.getChords(song.lyrics).toList())
            _song.postValue(song)
            delay(ADD_TO_HISTORY_DELAY)
            songRepository.addSongToLastPlayed(song)
        }
    }

    fun editSong() {
        song.value?.id?.let(songsNavigator::navigateToEditSong)
    }

}