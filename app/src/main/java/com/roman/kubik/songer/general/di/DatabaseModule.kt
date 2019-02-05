package com.roman.kubik.songer.general.di

import android.content.Context
import androidx.room.Room
import com.roman.kubik.songer.Constants
import com.roman.kubik.songer.data.category.CategoryModelMapper
import com.roman.kubik.songer.data.database.AppDatabase
import com.roman.kubik.songer.data.database.DatabaseMigrations.MIGRATION_1_2
import com.roman.kubik.songer.data.database.DatabaseMigrations.MIGRATION_2_3
import com.roman.kubik.songer.data.favourite.FavouriteModelMapper
import com.roman.kubik.songer.data.song.SongModelMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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
    internal fun getAppDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, Constants.APP_DB_FILE_NAME)
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .build()

    @Provides
    @Singleton
    internal fun getSongDao(appDatabase: AppDatabase) = appDatabase.songDao()

    @Provides
    @Singleton
    internal fun getCategoryDao(appDatabase: AppDatabase) = appDatabase.categoryDao()

    @Provides
    @Singleton
    internal fun getFavouriteDao(appDatabase: AppDatabase) = appDatabase.favouriteDao()

    @Provides
    @Singleton
    internal fun getHistoryDao(appDatabase: AppDatabase) = appDatabase.historyDao()

    @Provides
    @Singleton
    internal fun getDeletionDao(appDatabase: AppDatabase) = appDatabase.deletionDao()
}
