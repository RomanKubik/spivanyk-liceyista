package com.roman.kubik.spivanyklicejista.data.favourite;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by kubik on 1/20/18.
 */

@Dao
public interface FavouriteDao {

    @Query("SELECT * FROM favourite")
    Single<List<FavouriteEntity>> getAll();

    @Query("INSERT INTO favourite (song_id) VALUES (:songId)")
    Completable add(int songId);

    @Query("DELETE FROM favourite WHERE favourite.song_id = :songId")
    Completable delete(int songId);
}
