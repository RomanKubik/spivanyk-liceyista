package com.roman.kubik.songer.data.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public final class DatabaseMigrations {

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
        }
    };

    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Fix history table
            database.execSQL("ALTER TABLE `history` RENAME TO `_history_old`;");
            database.execSQL("CREATE TABLE `history`\n" +
                    "(\n" +
                    "`id` INTEGER NOT NULL PRIMARY KEY,\n" +
                    "`song_id` INTEGER NOT NULL,\n" +
                    "CONSTRAINT `fk_song`\n" +
                    "FOREIGN KEY (`song_id`)\n" +
                    "REFERENCES song(`id`)\n" +
                    "ON DELETE CASCADE" +
                    ");");
            database.execSQL("INSERT INTO `history` SELECT * FROM `_history_old`;");
            database.execSQL("DROP TABLE `_history_old`;");

            // Fix favourite table
            database.execSQL("ALTER TABLE `favourite` RENAME TO `_favourite_old`;");
            database.execSQL("CREATE TABLE `favourite`\n" +
                    "(\n" +
                    "`id` INTEGER NOT NULL PRIMARY KEY,\n" +
                    "`song_id` INTEGER NOT NULL,\n" +
                    "CONSTRAINT `fk_song`\n" +
                    "FOREIGN KEY (`song_id`)\n" +
                    "REFERENCES song(`id`)\n" +
                    "ON DELETE CASCADE" +
                    ");");
            database.execSQL("INSERT INTO `favourite` SELECT * FROM `_favourite_old`;");
            database.execSQL("DROP TABLE `_favourite_old`;");
        }
    };

    public static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Fix history table
            database.execSQL("ALTER TABLE `history` RENAME TO `_history_old`;");
            database.execSQL("CREATE TABLE `history`\n" +
                    "(\n" +
                    "`song_id` INTEGER NOT NULL PRIMARY KEY,\n" +
                    "`timestamp` INTEGER NOT NULL DEFAULT 0,\n" +
                    "CONSTRAINT `fk_song`\n" +
                    "FOREIGN KEY (`song_id`)\n" +
                    "REFERENCES song(`id`)\n" +
                    "ON DELETE CASCADE" +
                    ");");
            database.execSQL("INSERT INTO `history` SELECT DISTINCT `song_id`, 0 FROM `_history_old`;");
            database.execSQL("DROP TABLE `_history_old`;");
        }
    };

}
