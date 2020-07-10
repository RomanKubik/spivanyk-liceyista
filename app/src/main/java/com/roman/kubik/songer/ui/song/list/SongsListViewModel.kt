package com.roman.kubik.songer.ui.song.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.roman.kubik.songer.domain.repository.SongRepository
import com.roman.kubik.songer.domain.song.Song
import com.roman.kubik.songer.core.ui.base.BaseViewModel
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