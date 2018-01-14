package com.roman.kubik.spivanyklicejista.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.roman.kubik.spivanyklicejista.data.category.CategoryEntity;
import com.roman.kubik.spivanyklicejista.data.song.SongDao;
import com.roman.kubik.spivanyklicejista.data.song.SongEntity;

/**
 * Room database representation
 * Created by kubik on 1/14/18.
 */
@Database(entities = {SongEntity.class, CategoryEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract SongDao songDao();

}