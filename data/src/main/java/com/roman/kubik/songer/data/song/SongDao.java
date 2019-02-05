package com.roman.kubik.songer.data.song;

import com.roman.kubik.songer.domain.category.Category;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Simple Room Song DAO
 * Created by kubik on 1/14/18.
 */

@Dao
public interface SongDao {

    @Query("SELECT song.id, song.category_id, song.lyrics, song.title  FROM song LEFT JOIN deletion ON song.id == deletion.song_id WHERE deletion.song_id IS NULL ORDER BY song.title")
    Single<List<SongEntity>> getAll();

    @Query("SELECT song.id, song.category_id, song.lyrics, song.title FROM song LEFT JOIN deletion ON deletion.song_id == song.id WHERE deletion.song_id IS NULL AND song.category_id = :categoryId ORDER BY song.title")
    Single<List<SongEntity>> getAllByCategory(int categoryId);

    @Query("SELECT song.id, song.category_id, song.lyrics, song.title FROM song LEFT JOIN deletion ON deletion.song_id == song.id WHERE deletion.song_id IS NULL AND (song.title LIKE :query OR song.lyrics LIKE :query) ORDER BY song.title")
    Single<List<SongEntity>> search(String query);

    @Query("SELECT song.id, song.category_id, song.lyrics, song.title FROM song LEFT JOIN deletion ON deletion.song_id == song.id WHERE deletion.song_id IS NULL AND (song.title LIKE :query OR song.lyrics LIKE :query) AND song.category_id = :categoryId ORDER BY song.title")
    Single<List<SongEntity>> search(String query, @Category.CategoryId int categoryId);

    @Query("SELECT * FROM song WHERE song.id = :id LIMIT 1")
    Maybe<SongEntity> getById(int id);

    @Query("SELECT count(song.id) FROM song LEFT JOIN deletion ON song.id == deletion.song_id WHERE deletion.song_id IS NULL")
    Single<Integer> getCount();

    @Query("SELECT count(song.id) FROM song LEFT JOIN deletion ON song.id == deletion.song_id WHERE deletion.song_id IS NULL AND song.category_id = :categoryId")
    Single<Integer> getCountByCategoryId(int categoryId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdate(SongEntity song) ;

    @Delete
    void delete(SongEntity song);

}
