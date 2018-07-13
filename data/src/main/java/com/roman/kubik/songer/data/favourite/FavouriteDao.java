package com.roman.kubik.songer.data.favourite;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.roman.kubik.songer.domain.favourite.Favourite;
import com.roman.kubik.songer.domain.song.Song;

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

    @Insert
    void add(FavouriteEntity favouriteEntity);

    @Query("DELETE FROM favourite WHERE favourite.song_id = :songId")
    void delete(int songId);

    @Query("SELECT * FROM favourite WHERE favourite.song_id = :songId")
    Single<FavouriteEntity> isSongExists(int songId);

    @Query("SELECT count(id) FROM favourite")
    Single<Integer> getCount();
}
