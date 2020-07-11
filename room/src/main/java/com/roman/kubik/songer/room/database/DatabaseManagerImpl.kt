package com.roman.kubik.songer.room.database

import android.content.Context
import androidx.room.Room
import com.roman.kubik.songer.room.database.DatabaseMigrations.MIGRATION_1_2
import com.roman.kubik.songer.room.database.DatabaseMigrations.MIGRATION_2_3
import com.roman.kubik.songer.room.database.DatabaseMigrations.MIGRATION_3_4
import com.roman.kubik.songer.room.database.DatabaseMigrations.MIGRATION_4_5
import javax.inject.Inject


class DatabaseManagerImpl @Inject constructor(
        private val context: Context,
        private val databaseCopyHelper: DatabaseCopyHelper,
        private val appDatabase: AppDatabase
) : DatabaseManager {

    override suspend fun reset() {
        TODO("Not yet implemented")
    }

    override suspend fun createDatabase() {
        databaseCopyHelper.createDataBase()
    }

    companion object {
        fun generateAppDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DatabaseManager.DB_NAME)
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5)
                    .build()
        }
    }
}