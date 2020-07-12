package com.roman.kubik.songer.room.favourite

import androidx.room.Dao
import androidx.room.Query
import com.roman.kubik.songer.room.song.SongEntity

@Dao
interface FavouriteDao {

    @Query("SELECT * FROM favourite INNER JOIN song ON favourite.song_id = song.id ORDER BY song.title")
    suspend fun getAll(): List<SongEntity>
}