package com.roman.kubik.songer.room.song

import com.roman.kubik.songer.room.category.CategoryEntity
import com.roman.kubik.songer.room.favourite.FavouriteDao
import com.roman.kubik.songer.room.history.HistoryDao
import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.domain.song.SongCategory
import com.roman.kubik.songer.songs.domain.song.SongsService
import javax.inject.Inject

class RoomSongService @Inject constructor(
        private val songDao: SongDao,
        private val historyDao: HistoryDao,
        private val favouriteDao: FavouriteDao
) : SongsService {

    override suspend fun getAllSongs(): List<Song> {
        return songDao.getAll().map { it.toSong() }
    }

    override suspend fun getAllSongs(songCategory: SongCategory): List<Song> {
        return when (songCategory) {
            SongCategory.PATRIOTIC -> songDao.getAllByCategory(CategoryEntity.CATEGORY_PATRIOTIC).map { it.toSong() }
            SongCategory.BONFIRE -> songDao.getAllByCategory(CategoryEntity.CATEGORY_BONFIRE).map { it.toSong() }
            SongCategory.ABROAD -> songDao.getAllByCategory(CategoryEntity.CATEGORY_ABROAD).map { it.toSong() }
            SongCategory.MY_SONGS -> songDao.getAllByCategory(CategoryEntity.CATEGORY_USERS).map { it.toSong() }
            else -> getAllSongs()
        }
    }

    override suspend fun getLastPlayedSongs(): List<Song> {
        return historyDao.getHistory().map { it.toSong() }
    }

    override suspend fun getFavouriteSongs(): List<Song> {
        return favouriteDao.getAll().map { it.toSong() }
    }

    override suspend fun searchSongs(query: String): List<Song> {
        return songDao.search("%$query%").map { it.toSong() }
    }

    override suspend fun getSongById(songId: String): Song {
        return songDao.getSongById(songId).toSong()
    }

    override suspend fun createOrUpdateSong(song: Song) {
        songDao.createOrUpdateSong(song.toSongEntity())
    }
}