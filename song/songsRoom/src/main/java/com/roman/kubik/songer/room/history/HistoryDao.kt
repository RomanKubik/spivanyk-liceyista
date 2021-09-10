package com.roman.kubik.songer.room.history

import androidx.room.*
import com.roman.kubik.songer.room.song.SongEntity


@Dao
interface HistoryDao {

    @Query("SELECT * FROM history INNER JOIN song ON song.id = history.song_id ORDER BY timestamp DESC")
    suspend fun getHistory(): List<SongEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToHistory(historyEntity: HistoryEntity)

}