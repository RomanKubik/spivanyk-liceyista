package com.roman.kubik.songer.songs.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.roman.kubik.songer.chords.ChordsFormatter
import com.roman.kubik.songer.core.navigation.SearchNavigator
import com.roman.kubik.songer.core.ui.base.search.BaseSearchViewModel
import com.roman.kubik.songer.chords.ChordsImageMapper
import com.roman.kubik.songer.chords.model.Chord
import com.roman.kubik.songer.songs.domain.repository.SongRepository
import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.navigation.SongsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SongDetailsViewModel @ViewModelInject constructor(
        private val songRepository: SongRepository,
        private val songsNavigator: SongsNavigator,
        searchNavigator: SearchNavigator
) : BaseSearchViewModel(searchNavigator) {

    private val _song = MutableLiveData<Song>()
    private val _chords = MutableLiveData<List<Chord>>()
    val song: LiveData<Song> = _song
    val chords: LiveData<List<Chord>> = _chords

    fun loadSong(songId: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val song = songRepository.getSongById(songId)
            _chords.postValue(ChordsImageMapper.getChords(song.lyrics).toList())
            _song.postValue(song)
        }
    }

    fun editSong() {
        song.value?.id?.let(songsNavigator::navigateToEditSong)
    }

}