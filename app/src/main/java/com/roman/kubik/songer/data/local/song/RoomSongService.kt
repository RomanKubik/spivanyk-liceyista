package com.roman.kubik.songer.data.local.song

import com.roman.kubik.songer.domain.song.Song
import com.roman.kubik.songer.domain.song.SongsService
import javax.inject.Inject

class RoomSongService @Inject constructor(private val songDao: SongDao) : SongsService {

    override suspend fun getAllSongs(): List<Song> {
        return songDao.getAll().map { Song(it.id, it.title, it.lyrics) }
    }
}