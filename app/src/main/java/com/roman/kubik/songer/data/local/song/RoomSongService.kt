package com.roman.kubik.songer.data.local.song

import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.domain.song.SongsService
import javax.inject.Inject

class RoomSongService @Inject constructor(private val songDao: SongDao) : com.roman.kubik.songer.songs.domain.song.SongsService {

    override suspend fun getAllSongs(): List<com.roman.kubik.songer.songs.domain.song.Song> {
        return songDao.getAll().map { com.roman.kubik.songer.songs.domain.song.Song(it.id, it.title, it.lyrics) }
    }
}