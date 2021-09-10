package com.roman.kubik.songer.room.song

import com.roman.kubik.songer.room.category.CategoryEntity
import com.roman.kubik.songer.room.favourite.FavouriteDao
import com.roman.kubik.songer.room.favourite.FavouriteEntity
import com.roman.kubik.songer.room.history.HistoryDao
import com.roman.kubik.songer.room.history.HistoryEntity
import com.roman.kubik.songer.room.lastadded.LastAddedDao
import com.roman.kubik.songer.room.lastadded.LastAddedEntity
import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.domain.song.SongCategory
import com.roman.kubik.songer.songs.domain.song.SongsService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomSongService @Inject constructor(
        private val songDao: SongDao,
        private val historyDao: HistoryDao,
        private val favouriteDao: FavouriteDao,
        private val lastAddedDao: LastAddedDao
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
        val isFavourite = favouriteDao.isSongExists(songId) != null
        return songDao.getSongById(songId).toSong(isFavourite)
    }

    override suspend fun createOrUpdateSong(song: Song) {
        songDao.createOrUpdateSong(song.toSongEntity())
        if (song.isFavourite) {
            favouriteDao.add(FavouriteEntity(song.id))
        } else {
            favouriteDao.delete(song.id)
        }

    }

    override suspend fun addToLastPlayed(song: Song) {
        val historyEntity = HistoryEntity(song.id, System.currentTimeMillis())
        historyDao.addToHistory(historyEntity)
    }

    override suspend fun removeSong(song: Song) {
        songDao.delete(song.toSongEntity())
    }

    override suspend fun getLastAddedSongs(): List<Song> {
        return lastAddedDao.getLastAddedSongs().map { it.toSong() }
    }

    override suspend fun addSongsToLastAdded(songs: List<Song>) {
        lastAddedDao.addNewSongs(songs.map { LastAddedEntity(it.id) })
    }

    override suspend fun clearLastAddedSongs() {
        lastAddedDao.clearAll()
    }
}