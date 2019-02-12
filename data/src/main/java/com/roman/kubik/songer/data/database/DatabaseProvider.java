package com.roman.kubik.songer.data.database;

import android.content.Context;

import androidx.room.Room;

import static com.roman.kubik.songer.data.database.DatabaseMigrations.MIGRATION_1_2;
import static com.roman.kubik.songer.data.database.DatabaseMigrations.MIGRATION_2_3;

public final class DatabaseProvider {

    private static AppDatabase appDatabase;

    public static AppDatabase getAppDatabase(Context context) {
        if (appDatabase == null) {
            synchronized (DatabaseProvider.class) {
                if (appDatabase == null) {
                    appDatabase = generateAppDatabase(context);
                }
            }
        }
        return appDatabase;
    }

    public static synchronized void updateAppDatabase(Context context) {
        appDatabase = generateAppDatabase(context);
    }

    private static AppDatabase generateAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "spivanyk.db")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build();
    }

}
