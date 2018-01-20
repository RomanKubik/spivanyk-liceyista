package com.roman.kubik.spivanyklicejista.general.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.roman.kubik.spivanyklicejista.Constants;
import com.roman.kubik.spivanyklicejista.data.category.CategoryDao;
import com.roman.kubik.spivanyklicejista.data.category.CategoryModelMapper;
import com.roman.kubik.spivanyklicejista.data.chord.ChordDao;
import com.roman.kubik.spivanyklicejista.data.chord.ChordModelMapper;
import com.roman.kubik.spivanyklicejista.data.database.AppDatabase;
import com.roman.kubik.spivanyklicejista.data.database.DatabaseCopyHelper;
import com.roman.kubik.spivanyklicejista.data.favourite.FavouriteDao;
import com.roman.kubik.spivanyklicejista.data.favourite.FavouriteModelMapper;
import com.roman.kubik.spivanyklicejista.data.song.SongDao;
import com.roman.kubik.spivanyklicejista.data.song.SongModelMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Database module
 * Provides Database Singleton. Provides all Dao Databases.
 * Created by kubik on 1/20/18.
 */

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    AppDatabase getAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, Constants.APP_DB_FILE_NAME)
                .build();
    }

    @Provides
    @Singleton
    SongDao getSongDao(AppDatabase appDatabase) {
        return appDatabase.songDao();
    }

    @Provides
    @Singleton
    CategoryDao getCategoryDao(AppDatabase appDatabase) {
        return appDatabase.categoryDao();
    }

    @Provides
    @Singleton
    ChordDao getChordDao(AppDatabase appDatabase) {
        return appDatabase.chordDao();
    }

    @Provides
    @Singleton
    FavouriteDao getFavouriteDao(AppDatabase appDatabase) {
        return appDatabase.favouriteDao();
    }

    @Provides
    @Singleton
    SongModelMapper getSongModelMapper() {
        return new SongModelMapper();
    }

    @Provides
    @Singleton
    CategoryModelMapper getCategoryModelMapper() {
        return new CategoryModelMapper();
    }

    @Provides
    @Singleton
    FavouriteModelMapper getFavouriteModelMapper() {
        return new FavouriteModelMapper();
    }

    @Provides
    @Singleton
    ChordModelMapper getChordModelMapper() {
        return new ChordModelMapper();
    }

}
