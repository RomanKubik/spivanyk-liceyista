package com.roman.kubik.songer.data.favourite;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.roman.kubik.songer.data.song.SongEntity;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by kubik on 1/20/18.
 */

@Dao
public interface FavouriteDao {

    @Query("SELECT * FROM favourite INNER JOIN song ON favourite.song_id = song.id ORDER BY song.title")
    Single<List<SongEntity>> getAll();

    @Query("SELECT * FROM favourite INNER JOIN song ON song.id = favourite.song_id WHERE song.title LIKE :query OR song.lyrics LIKE :query ORDER BY song.title")
    Single<List<SongEntity>> search(String query);

    @Insert
    void add(FavouriteEntity favouriteEntity);

    @Query("DELETE FROM favourite WHERE favourite.song_id = :songId")
    void delete(String songId);

    @Query("SELECT * FROM favourite WHERE favourite.song_id = :songId")
    Single<FavouriteEntity> isSongExists(String songId);

    @Query("SELECT count(id) FROM favourite")
    Single<Integer> getCount();
}
