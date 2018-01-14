package com.roman.kubik.spivanyklicejista.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.roman.kubik.spivanyklicejista.song.SongDao;

/**
 * Provides selected DAO instance
 * Created by kubik on 1/14/18.
 */

public class DatabaseDaoProvider {

    private static DatabaseDaoProvider instance;
    private AppDatabase appDatabase;

    private DatabaseDaoProvider(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "spivanyk.db")
                .build();
    }

    public static DatabaseDaoProvider getInstance(Context context) {
        if (instance == null)
            instance = new DatabaseDaoProvider(context);
        return instance;
    }

    public SongDao songDao() {
        return appDatabase.songDao();
    }
}
