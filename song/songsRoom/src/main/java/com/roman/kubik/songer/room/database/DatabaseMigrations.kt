package com.roman.kubik.songer.room.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.roman.kubik.songer.room.category.CategoryEntity.Companion.CATEGORY_ABROAD


object DatabaseMigrations {
    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {}
    }
    val MIGRATION_2_3: Migration = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Fix history table
            database.execSQL("ALTER TABLE `history` RENAME TO `_history_old`;")
            database.execSQL(
                """
    CREATE TABLE `history`
    (
    `id` INTEGER NOT NULL PRIMARY KEY,
    `song_id` INTEGER NOT NULL,
    CONSTRAINT `fk_song`
    FOREIGN KEY (`song_id`)
    REFERENCES song(`id`)
    ON DELETE CASCADE);
    """.trimIndent()
            )
            database.execSQL("INSERT INTO `history` SELECT * FROM `_history_old`;")
            database.execSQL("DROP TABLE `_history_old`;")

            // Fix favourite table
            database.execSQL("ALTER TABLE `favourite` RENAME TO `_favourite_old`;")
            database.execSQL(
                """
    CREATE TABLE `favourite`
    (
    `id` INTEGER NOT NULL PRIMARY KEY,
    `song_id` INTEGER NOT NULL,
    CONSTRAINT `fk_song`
    FOREIGN KEY (`song_id`)
    REFERENCES song(`id`)
    ON DELETE CASCADE);
    """.trimIndent()
            )
            database.execSQL("INSERT INTO `favourite` SELECT * FROM `_favourite_old`;")
            database.execSQL("DROP TABLE `_favourite_old`;")
        }
    }
    val MIGRATION_3_4: Migration = object : Migration(3, 4) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Fix history table
            database.execSQL("ALTER TABLE `history` RENAME TO `_history_old`;")
            database.execSQL(
                """
    CREATE TABLE `history`
    (
    `song_id` INTEGER NOT NULL PRIMARY KEY,
    `timestamp` INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT `fk_song`
    FOREIGN KEY (`song_id`)
    REFERENCES song(`id`)
    ON DELETE CASCADE);
    """.trimIndent()
            )
            database.execSQL("INSERT INTO `history` SELECT DISTINCT `song_id`, 0 FROM `_history_old`;")
            database.execSQL("DROP TABLE `_history_old`;")
        }
    }
    val MIGRATION_4_5: Migration = object : Migration(4, 5) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Fix song ids
            database.execSQL("ALTER TABLE `song` RENAME TO `_song_old`;")
            database.execSQL("ALTER TABLE `history` RENAME TO `_history_old`;")
            database.execSQL("ALTER TABLE `favourite` RENAME TO `_favourite_old`;")
            database.execSQL(
                """
    CREATE TABLE `song`
    (
    `id` TEXT NOT NULL PRIMARY KEY,
    `id_old` INTEGER NOT NULL,
    `title` TEXT NOT NULL,
    `lyrics` TEXT NOT NULL,
    `category_id` INTEGER NOT NULL,
    CONSTRAINT `fk_category`
    FOREIGN KEY (`category_id`)
    REFERENCES category(`id`)
    ON DELETE NO ACTION);
    """.trimIndent()
            )
            database.execSQL(
                """
    CREATE TABLE `favourite`
    (
    `id` TEXT NOT NULL PRIMARY KEY,
    `song_id` TEXT NOT NULL,
    CONSTRAINT `fk_song`
    FOREIGN KEY (`song_id`)
    REFERENCES song(`id`)
    ON DELETE CASCADE);
    """.trimIndent()
            )
            database.execSQL(
                """
    CREATE TABLE `history`
    (
    `song_id` TEXT NOT NULL PRIMARY KEY,
    `timestamp` INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT `fk_song`
    FOREIGN KEY (`song_id`)
    REFERENCES song(`id`)
    ON DELETE CASCADE);
    """.trimIndent()
            )
            database.execSQL(
                "INSERT INTO `song` " +
                        "SELECT lower(hex(randomblob(16))), `id`, `title`, `lyrics`, `category_id` FROM `_song_old`;"
            )
            database.execSQL(
                "INSERT INTO `favourite` " +
                        "SELECT lower(hex(randomblob(16))), `song`.`id` FROM `song` " +
                        "INNER JOIN `_favourite_old` ON `_favourite_old`.`song_id` = `song`.`id_old`"
            )
            database.execSQL(
                "INSERT INTO `history` " +
                        "SELECT `song`.`id`, `_history_old`.`timestamp` FROM `_history_old` " +
                        "INNER JOIN `song` ON `_history_old`.`song_id` = `song`.`id_old`"
            )
            database.execSQL("ALTER TABLE `song` RENAME TO `_song_old_old`;")
            database.execSQL(
                """
    CREATE TABLE `song`
    (
    `id` TEXT NOT NULL PRIMARY KEY,
    `title` TEXT NOT NULL,
    `lyrics` TEXT NOT NULL,
    `category_id` INTEGER NOT NULL,
    CONSTRAINT `fk_category`
    FOREIGN KEY (`category_id`)
    REFERENCES category(`id`)
    ON DELETE NO ACTION);
    """.trimIndent()
            )
            database.execSQL(
                "INSERT INTO `song`" +
                        "SELECT `id`, `title`, `lyrics`, `category_id` FROM `_song_old_old`;"
            )
            database.execSQL("INSERT INTO `category` VALUES (5, 'web')")
            database.execSQL("DROP TABLE `_song_old`;")
            database.execSQL("DROP TABLE `_song_old_old`;")
            database.execSQL("DROP TABLE `_history_old`;")
            database.execSQL("DROP TABLE `_favourite_old`;")
            database.execSQL("CREATE INDEX `song_id_clustered_index` ON song(`id`)")
            database.execSQL("CREATE INDEX `song_title_nonclustered_index` ON song(`title`)")
        }
    }
    val MIGRATION_5_6: Migration = object : Migration(5, 6) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                """
    CREATE TABLE `lastAdded`
    (
    `song_id` TEXT NOT NULL PRIMARY KEY,
    CONSTRAINT `fk_song`
    FOREIGN KEY (`song_id`)
    REFERENCES song(`id`)
    ON DELETE CASCADE);
    """.trimIndent()
            )
        }
    }
}