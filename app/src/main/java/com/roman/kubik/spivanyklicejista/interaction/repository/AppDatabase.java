package com.roman.kubik.spivanyklicejista.interaction.repository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.roman.kubik.spivanyklicejista.interaction.entity.Category;
import com.roman.kubik.spivanyklicejista.interaction.entity.Song;

/**
 * Created by kubik on 12/25/17.
 */

@Database(entities = {Song.class, Category.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SongDao songDao();
}