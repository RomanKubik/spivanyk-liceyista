package com.roman.kubik.songer.songs.ui.edit

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
import kotlinx.coroutines.withContext

class EditSongViewModel @ViewModelInject constructor(
        private val songRepository: SongRepository,
        private var songsNavigator: SongsNavigator
) : BaseViewModel() {

    private val _song = MutableLiveData<Song?>(null)
    val song: LiveData<Song?> = _song

    fun loadSong(songId: String) {
        GlobalScope.launch(Dispatchers.IO) {
            _song.postValue(songRepository.getSongById(songId))
        }
    }

    fun save(title: String, lyrics: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val currentSong = song.value
            val song =
                    if (currentSong == null)
                        Song(title, lyrics, SongCategory.MY_SONGS)
                    else
                        Song(currentSong.id, title, lyrics, currentSong.category)
            songRepository.createOrUpdateSong(song)
            withContext(Dispatchers.Main) {
                songsNavigator.navigateUp()
            }
        }
    }

    fun recognize(lyrics: String) {

    }
}