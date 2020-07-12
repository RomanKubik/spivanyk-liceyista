package com.roman.kubik.songer.room.history

import androidx.room.Dao
import androidx.room.Query
import com.roman.kubik.songer.room.song.SongEntity


@Dao
interface HistoryDao {

    @Query("SELECT * FROM history INNER JOIN song ON song.id = history.song_id ORDER BY timestamp DESC")
    suspend fun getHistory(): List<SongEntity>

}