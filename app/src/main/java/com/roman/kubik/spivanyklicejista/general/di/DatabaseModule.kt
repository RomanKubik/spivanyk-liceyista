package com.roman.kubik.spivanyklicejista.general.di

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import android.content.Context

import com.roman.kubik.spivanyklicejista.Constants
import com.roman.kubik.spivanyklicejista.data.category.CategoryDao
import com.roman.kubik.spivanyklicejista.data.category.CategoryModelMapper
import com.roman.kubik.spivanyklicejista.data.chord.ChordDao
import com.roman.kubik.spivanyklicejista.data.chord.ChordModelMapper
import com.roman.kubik.spivanyklicejista.data.database.AppDatabase
import com.roman.kubik.spivanyklicejista.data.favourite.FavouriteDao
import com.roman.kubik.spivanyklicejista.data.favourite.FavouriteModelMapper
import com.roman.kubik.spivanyklicejista.data.song.SongDao
import com.roman.kubik.spivanyklicejista.data.song.SongModelMapper

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Database module
 * Provides Database Singleton. Provides all Dao Databases.
 * Created by kubik on 1/20/18.
 */

@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun getSongModelMapper() = SongModelMapper()

    @Provides
    @Singleton
    internal fun getCategoryModelMapper() = CategoryModelMapper()

    @Provides
    @Singleton
    internal fun getFavouriteModelMapper() = FavouriteModelMapper()

    @Provides
    @Singleton
    internal fun getChordModelMapper() = ChordModelMapper()

    @Provides
    @Singleton
    internal fun getAppDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, Constants.APP_DB_FILE_NAME)
                    .addMigrations(object : Migration(1, 2) {
                        override fun migrate(database: SupportSQLiteDatabase) {
                        }
                    })
                    .build()

    @Provides
    @Singleton
    internal fun getSongDao(appDatabase: AppDatabase) = appDatabase.songDao()

    @Provides
    @Singleton
    internal fun getCategoryDao(appDatabase: AppDatabase) = appDatabase.categoryDao()

    @Provides
    @Singleton
    internal fun getChordDao(appDatabase: AppDatabase) = appDatabase.chordDao()

    @Provides
    @Singleton
    internal fun getFavouriteDao(appDatabase: AppDatabase) = appDatabase.favouriteDao()

}
