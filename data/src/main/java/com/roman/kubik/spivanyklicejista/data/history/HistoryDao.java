package com.roman.kubik.spivanyklicejista.data.history;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;


@Dao
public interface HistoryDao {

    @Query("SELECT * FROM history")
    Single<List<HistoryEntity>> getHistory();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addToHistory(HistoryEntity historyEntity);
}
