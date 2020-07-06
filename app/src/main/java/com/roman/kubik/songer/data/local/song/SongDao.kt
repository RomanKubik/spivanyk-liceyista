package com.roman.kubik.songer.data.local.song

import androidx.room.Dao
import androidx.room.Query

@Dao
interface SongDao {

    @Query("SELECT * FROM song ORDER BY song.title")
    suspend fun getAll(): List<SongEntity>

}