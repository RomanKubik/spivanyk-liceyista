package com.roman.kubik.songer.room.song

import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.domain.song.SongsService
import javax.inject.Inject

class RoomSongService @Inject constructor(private val songDao: SongDao) : SongsService {

    override suspend fun getAllSongs(): List<Song> {
        return songDao.getAll().map { it.toSong() }
    }

    override suspend fun getSongById(songId: String): Song {
        return songDao.getSongById(songId).toSong()
    }
}