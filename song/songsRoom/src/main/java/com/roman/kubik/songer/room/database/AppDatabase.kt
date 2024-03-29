package com.roman.kubik.songer.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.roman.kubik.songer.room.category.CategoryEntity
import com.roman.kubik.songer.room.favourite.FavouriteDao
import com.roman.kubik.songer.room.favourite.FavouriteEntity
import com.roman.kubik.songer.room.history.HistoryDao
import com.roman.kubik.songer.room.history.HistoryEntity
import com.roman.kubik.songer.room.lastadded.LastAddedDao
import com.roman.kubik.songer.room.lastadded.LastAddedEntity
import com.roman.kubik.songer.room.song.SongDao
import com.roman.kubik.songer.room.song.SongEntity

/**
 * Room database representation
 */
@Database(entities = [SongEntity::class, CategoryEntity::class, FavouriteEntity::class, HistoryEntity::class, LastAddedEntity::class], version = 6, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun songDao(): SongDao

    abstract fun favouriteDao(): FavouriteDao

    abstract fun historyDao(): HistoryDao

    abstract fun lastAddedDao(): LastAddedDao
}