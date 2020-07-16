package com.roman.kubik.songer.songs.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.roman.kubik.songer.core.navigation.SearchNavigator
import com.roman.kubik.songer.core.ui.base.search.BaseSearchViewModel
import com.roman.kubik.songer.songs.domain.repository.SongRepository
import com.roman.kubik.songer.songs.domain.song.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SongDetailsViewModel @ViewModelInject constructor(
        private val songRepository: SongRepository,
        searchNavigator: SearchNavigator
) : BaseSearchViewModel(searchNavigator) {

    private val _song = MutableLiveData<Song>()
    val song: LiveData<Song> = _song

    fun loadSong(songId: String) {
        GlobalScope.launch(Dispatchers.IO) {
            _song.postValue(songRepository.getSongById(songId))
        }
    }

}