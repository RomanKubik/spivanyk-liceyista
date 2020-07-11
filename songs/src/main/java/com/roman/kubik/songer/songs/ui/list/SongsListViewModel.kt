package com.roman.kubik.songer.songs.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.roman.kubik.songer.core.ui.base.BaseViewModel
import com.roman.kubik.songer.songs.domain.repository.SongRepository
import com.roman.kubik.songer.songs.domain.song.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SongsListViewModel @ViewModelInject constructor(private val songRepository: SongRepository) : BaseViewModel() {

    private val _songs = MutableLiveData<List<Song>>(emptyList())
    val songs: LiveData<List<Song>> = _songs

    init {
        GlobalScope.launch(Dispatchers.IO) {
            _songs.postValue(songRepository.getAllSongs())
        }
    }

}