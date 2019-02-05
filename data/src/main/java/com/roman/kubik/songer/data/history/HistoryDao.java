package com.roman.kubik.songer.data.history;

import com.roman.kubik.songer.data.song.SongEntity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;


@Dao
public interface HistoryDao {

    @Query("SELECT * FROM history INNER JOIN song ON song.id = history.song_id LEFT JOIN deletion ON song.id == deletion.song_id WHERE deletion.song_id IS NULL")
    Single<List<SongEntity>> getHistory();

    @Query("SELECT * FROM history INNER JOIN song ON song.id = history.song_id LEFT JOIN deletion ON song.id == deletion.song_id WHERE deletion.song_id IS NULL AND song.title LIKE :query OR song.lyrics LIKE :query ORDER BY song.title")
    Single<List<SongEntity>> search(String query);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addToHistory(HistoryEntity historyEntity);
}
