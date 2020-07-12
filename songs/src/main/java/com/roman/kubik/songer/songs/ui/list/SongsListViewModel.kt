package com.roman.kubik.songer.songs.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.roman.kubik.songer.core.ui.base.BaseViewModel
import com.roman.kubik.songer.songs.domain.repository.SongRepository
import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.domain.song.SongCategory
import com.roman.kubik.songer.songs.navigation.SongsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SongsListViewModel @ViewModelInject constructor(
        private val songRepository: SongRepository,
        private val songsNavigator: SongsNavigator
) : BaseViewModel() {

    private val _songs = MutableLiveData<List<Song>>(emptyList())
    val songs: LiveData<List<Song>> = _songs

    fun loadSongs(categoryId: String?) {
        GlobalScope.launch(Dispatchers.IO) {
            val result = if (categoryId == null) {
                songRepository.getAllSongs()
            } else {
                songRepository.getAllSongs(SongCategory.valueOf(categoryId))
            }
            _songs.postValue(result)
        }
    }

    fun selectSong(song: Song) {
        songsNavigator.navigateToSongDetails(song.id)
    }

}