package com.roman.kubik.songer.data.history;

import com.roman.kubik.songer.data.song.SongEntity;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;


@Dao
public interface HistoryDao {

    @Query("SELECT * FROM history INNER JOIN song ON song.id = history.song_id")
    Single<List<SongEntity>> getHistory();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addToHistory(HistoryEntity historyEntity);
}
