package com.roman.kubik.songer.room.lastadded

import androidx.room.*
import com.roman.kubik.songer.room.song.SongEntity

@Dao
interface LastAddedDao {

    @Query("SELECT * FROM lastAdded INNER JOIN song ON song.id = lastAdded.song_id ORDER BY song.title")
    suspend fun getLastAddedSongs(): List<SongEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewSongs(lastAdded: List<LastAddedEntity>)

    @Query("DELETE FROM lastAdded")
    suspend fun clearAll()

}