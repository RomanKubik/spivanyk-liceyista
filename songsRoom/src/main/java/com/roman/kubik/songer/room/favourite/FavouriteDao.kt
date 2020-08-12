package com.roman.kubik.songer.room.favourite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.roman.kubik.songer.room.song.SongEntity

@Dao
interface FavouriteDao {

    @Query("SELECT * FROM favourite INNER JOIN song ON favourite.song_id = song.id ORDER BY song.title")
    suspend fun getAll(): List<SongEntity>

    @Query("SELECT * FROM favourite WHERE favourite.song_id = :songId")
    suspend fun isSongExists(songId: String): FavouriteEntity?

    @Insert
    fun add(favouriteEntity: FavouriteEntity?)

    @Query("DELETE FROM favourite WHERE favourite.song_id = :songId")
    fun delete(songId: String?)

}