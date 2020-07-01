package com.roman.kubik.songer.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Room database representation
 */
@Database(entities = [/*SongEntity::class, CategoryEntity::class, FavouriteEntity::class, HistoryEntity::class*/], version = 5, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
//    abstract fun songDao(): SongDao?
//    abstract fun categoryDao(): CategoryDao?
//    abstract fun favouriteDao(): FavouriteDao?
//    abstract fun historyDao(): HistoryDao?
}