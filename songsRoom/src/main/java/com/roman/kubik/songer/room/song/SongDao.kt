package com.roman.kubik.songer.room.song

import androidx.room.*

@Dao
interface SongDao {

    @Query("SELECT * FROM song ORDER BY song.title")
    suspend fun getAll(): List<SongEntity>

    @Query("SELECT * FROM song WHERE song.category_id = :categoryId ORDER BY song.title")
    suspend fun getAllByCategory(categoryId: Int): List<SongEntity>

    @Query("SELECT * FROM song WHERE song.title LIKE :query OR song.lyrics LIKE :query ORDER BY song.title")
    suspend fun search(query: String): List<SongEntity>

    @Query("SELECT * FROM song WHERE song.id = :songId LIMIT 1")
    suspend fun getSongById(songId: String): SongEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createOrUpdateSong(songEntity: SongEntity)

}