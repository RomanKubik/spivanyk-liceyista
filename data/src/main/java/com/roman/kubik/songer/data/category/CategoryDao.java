package com.roman.kubik.songer.data.category;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Simple Room Category DAO
 * Created by kubik on 1/20/18.
 */

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM category")
    Single<List<CategoryEntity>> getAll();

    @Query("SELECT * FROM category WHERE category.id = :id")
    Maybe<CategoryEntity> getById(int id);

}
