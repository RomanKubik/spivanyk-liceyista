package com.roman.kubik.songer.songs.ui.edit

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.roman.kubik.songer.core.ui.base.BaseViewModel
import com.roman.kubik.songer.chords.ChordsRecognizer
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
//            _song.postValue(songRepository.getSongById(songId))
        }
    }

    fun save(title: String, lyrics: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val song = updateSong(title, lyrics)
            songRepository.createOrUpdateSong(song)
            withContext(Dispatchers.Main) {
                songsNavigator.navigateUp()
            }
        }
    }

    fun recognize(title: String, lyrics: String) {
        _song.postValue(updateSong(title, ChordsRecognizer.recognizeChords(lyrics)))
    }

    private fun updateSong(title: String, lyrics: String): Song {
        val currentSong = song.value
        return currentSong?.copy(title = title, lyrics = lyrics)
                ?: Song(title, lyrics, SongCategory.MY_SONGS)
    }
}