package com.roman.kubik.songer.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.roman.kubik.songer.data.category.CategoryDao;
import com.roman.kubik.songer.data.category.CategoryEntity;
import com.roman.kubik.songer.data.favourite.FavouriteDao;
import com.roman.kubik.songer.data.favourite.FavouriteEntity;
import com.roman.kubik.songer.data.history.HistoryDao;
import com.roman.kubik.songer.data.history.HistoryEntity;
import com.roman.kubik.songer.data.song.local.SongDao;
import com.roman.kubik.songer.data.song.local.SongEntity;

/**
 * Room database representation
 * Created by kubik on 1/14/18.
 */
@Database(entities = {SongEntity.class, CategoryEntity.class, FavouriteEntity.class, HistoryEntity.class}, version = 5, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract SongDao songDao();

    public abstract CategoryDao categoryDao();

    public abstract FavouriteDao favouriteDao();

    public abstract HistoryDao historyDao();

}