package com.roman.kubik.spivanyklicejista.data.chord;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by kubik on 1/20/18.
 */

@Dao
public interface ChordDao {

    @Query("SELECT * FROM chord")
    Single<List<ChordEntity>> getAll();

    @Query("SELECT * FROM chord WHERE chord.id = :id")
    Maybe<ChordEntity> getById(int id);

    @Query("SELECT * FROM chord WHERE chord.name = :name")
    Maybe<ChordEntity> getByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(ChordEntity chord);

}
