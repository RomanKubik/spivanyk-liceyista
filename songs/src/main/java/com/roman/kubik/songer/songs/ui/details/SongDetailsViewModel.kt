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

    private val _song = MutableLiveData<SongDetails>()
    val song: LiveData<SongDetails> = _song

    fun loadSong(songId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val song = songRepository.getSongById(songId)
            val songDetails = SongDetails(song, ChordsImageMapper.getChords(song.lyrics).toList())
            _song.postValue(songDetails)
            delay(ADD_TO_HISTORY_DELAY)
            songRepository.addSongToLastPlayed(song)
        }
    }

    fun editSong() {
        song.value?.song?.id?.let(songsNavigator::navigateToEditSong)
    }

    fun likeDislikeSong() {
        song.value?.let {
            viewModelScope.launch(Dispatchers.IO) {
                val updatedSong = SongDetails(Song(it.song.id,
                        it.song.title,
                        it.song.lyrics,
                        it.song.category,
                        !it.song.isFavourite),
                        it.chords)
                songRepository.createOrUpdateSong(updatedSong.song)
                _song.postValue(updatedSong)
            }
        }
    }

    fun shareSong() {
        song.value?.song?.let(songsNavigator::shareSong)
    }

    data class SongDetails(
            val song: Song,
            val chords: List<Chord>
    )
}