package com.roman.kubik.songer.room.song

import androidx.room.Dao
import androidx.room.Query

@Dao
interface SongDao {

    @Query("SELECT * FROM song ORDER BY song.title")
    suspend fun getAll(): List<SongEntity>

    @Query("SELECT * FROM song WHERE song.category_id = :categoryId ORDER BY song.title")
    fun getAllByCategory(categoryId: Int): List<SongEntity>

    @Query("SELECT * FROM song WHERE song.id = :songId LIMIT 1")
    suspend fun getSongById(songId: String): SongEntity

}