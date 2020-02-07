package com.roman.kubik.songer.data.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.UUID;

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

    public static final Migration MIGRATION_4_5 = new Migration(4, 5) {

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Fix song ids
            database.execSQL("ALTER TABLE `song` RENAME TO `_song_old`;");
            database.execSQL("ALTER TABLE `history` RENAME TO `_history_old`;");
            database.execSQL("ALTER TABLE `favourite` RENAME TO `_favourite_old`;");

            database.execSQL("CREATE TABLE `song`\n" +
                    "(\n" +
                    "`id` TEXT NOT NULL PRIMARY KEY,\n" +
                    "`id_old` INTEGER NOT NULL,\n" +
                    "`title` TEXT NOT NULL,\n" +
                    "`lyrics` TEXT NOT NULL,\n" +
                    "`category_id` INTEGER NOT NULL,\n" +
                    "CONSTRAINT `fk_category`\n" +
                    "FOREIGN KEY (`category_id`)\n" +
                    "REFERENCES category(`id`)\n" +
                    "ON DELETE NO ACTION" +
                    ");");

            database.execSQL("CREATE TABLE `favourite`\n" +
                    "(\n" +
                    "`id` TEXT NOT NULL PRIMARY KEY,\n" +
                    "`song_id` TEXT NOT NULL,\n" +
                    "CONSTRAINT `fk_song`\n" +
                    "FOREIGN KEY (`song_id`)\n" +
                    "REFERENCES song(`id`)\n" +
                    "ON DELETE CASCADE" +
                    ");");

            database.execSQL("CREATE TABLE `history`\n" +
                    "(\n" +
                    "`song_id` TEXT NOT NULL PRIMARY KEY,\n" +
                    "`timestamp` INTEGER NOT NULL DEFAULT 0,\n" +
                    "CONSTRAINT `fk_song`\n" +
                    "FOREIGN KEY (`song_id`)\n" +
                    "REFERENCES song(`id`)\n" +
                    "ON DELETE CASCADE" +
                    ");");

            database.execSQL("INSERT INTO `song` " +
                    "SELECT lower(hex(randomblob(16))), `id`, `title`, `lyrics`, `category_id` FROM `_song_old`;");

            database.execSQL("INSERT INTO `favourite` " +
                    "SELECT lower(hex(randomblob(16))), `song`.`id` FROM `song` " +
                    "INNER JOIN `_favourite_old` ON `_favourite_old`.`song_id` = `song`.`id_old`");

            database.execSQL("INSERT INTO `history` " +
                    "SELECT `song`.`id`, `_history_old`.`timestamp` FROM `_history_old` " +
                    "INNER JOIN `song` ON `_history_old`.`song_id` = `song`.`id_old`");

            database.execSQL("ALTER TABLE `song` RENAME TO `_song_old_old`;");

            database.execSQL("CREATE TABLE `song`\n" +
                    "(\n" +
                    "`id` TEXT NOT NULL PRIMARY KEY,\n" +
                    "`title` TEXT NOT NULL,\n" +
                    "`lyrics` TEXT NOT NULL,\n" +
                    "`category_id` INTEGER NOT NULL,\n" +
                    "CONSTRAINT `fk_category`\n" +
                    "FOREIGN KEY (`category_id`)\n" +
                    "REFERENCES category(`id`)\n" +
                    "ON DELETE NO ACTION" +
                    ");");

            database.execSQL("INSERT INTO `song`" +
                    "SELECT `id`, `title`, `lyrics`, `category_id` FROM `_song_old_old`;");

            database.execSQL("INSERT INTO `category` VALUES (5, 'web')");

            database.execSQL("DROP TABLE `_song_old`;");
            database.execSQL("DROP TABLE `_song_old_old`;");
            database.execSQL("DROP TABLE `_history_old`;");
            database.execSQL("DROP TABLE `_favourite_old`;");

            database.execSQL("CREATE INDEX `song_id_clustered_index` ON song(`id`)");
            database.execSQL("CREATE INDEX `song_title_nonclustered_index` ON song(`title`)");
        }
    };

}
