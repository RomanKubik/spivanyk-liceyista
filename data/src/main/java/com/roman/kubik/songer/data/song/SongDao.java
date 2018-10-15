package com.roman.kubik.songer.data.song;

import com.roman.kubik.songer.domain.category.Category;

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

    @Query("SELECT * FROM song ORDER BY song.title")
    Single<List<SongEntity>> getAll();

    @Query("SELECT * FROM song WHERE song.category_id = :categoryId ORDER BY song.title")
    Single<List<SongEntity>> getAllByCategory(int categoryId);

    @Query("SELECT * FROM song WHERE song.title LIKE :query OR song.lyrics LIKE :query ORDER BY song.title")
    Single<List<SongEntity>> search(String query);

    @Query("SELECT * FROM song WHERE (song.title LIKE :query OR song.lyrics LIKE :query) AND song.category_id = :categoryId ORDER BY song.title")
    Single<List<SongEntity>> search(String query, @Category.CategoryId int categoryId);

    @Query("SELECT * FROM song WHERE song.id = :id LIMIT 1")
    Maybe<SongEntity> getById(int id);

    @Query("SELECT count(id) FROM song")
    Single<Integer> getCount();

    @Query("SELECT count(id) FROM song WHERE song.category_id = :categoryId")
    Single<Integer> getCountByCategoryId(int categoryId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdate(SongEntity song) ;

    @Delete
    void delete(SongEntity song);

}
