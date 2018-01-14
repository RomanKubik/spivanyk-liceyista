package com.roman.kubik.spivanyklicejista.data.song;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Simple Room Song DAO
 * Created by kubik on 1/14/18.
 */

@Dao
public interface SongDao {

    @Query("SELECT * FROM song")
    Single<List<SongEntity>> getAll();

    @Query("SELECT * FROM song WHERE song.category_id = :categoryId")
    Single<List<SongEntity>> getAllByCategory(int categoryId);

    @Query("SELECT * FROM song WHERE song.title LIKE :title")
    Single<List<SongEntity>> search(String title);

    @Query("SELECT * FROM song WHERE song.id = :id LIMIT 1")
    Maybe<SongEntity> getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdate(SongEntity song) ;

    @Delete
    void delete(SongEntity song);

}
