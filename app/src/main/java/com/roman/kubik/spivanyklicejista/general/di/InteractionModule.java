package com.roman.kubik.spivanyklicejista.general.di;

import com.roman.kubik.spivanyklicejista.data.category.CategoryDao;
import com.roman.kubik.spivanyklicejista.data.category.CategoryModelMapper;
import com.roman.kubik.spivanyklicejista.data.category.CategoryRepositoryImpl;
import com.roman.kubik.spivanyklicejista.data.chord.ChordDao;
import com.roman.kubik.spivanyklicejista.data.chord.ChordModelMapper;
import com.roman.kubik.spivanyklicejista.data.chord.ChordRepositoryImpl;
import com.roman.kubik.spivanyklicejista.data.favourite.FavouriteDao;
import com.roman.kubik.spivanyklicejista.data.favourite.FavouriteRepositoryImpl;
import com.roman.kubik.spivanyklicejista.data.song.SongDao;
import com.roman.kubik.spivanyklicejista.data.song.SongModelMapper;
import com.roman.kubik.spivanyklicejista.data.song.SongRepositoryImpl;
import com.roman.kubik.spivanyklicejista.domain.category.CategoryInteractor;
import com.roman.kubik.spivanyklicejista.domain.chord.ChordInteractor;
import com.roman.kubik.spivanyklicejista.domain.favourite.FavouriteInteractor;
import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kubik on 1/20/18.
 */

@Module
public class InteractionModule {

    @Provides
    @Singleton
    SongInteractor getSongInteractor(SongDao songDao, SongModelMapper songModelMapper) {
        return new SongInteractor(new SongRepositoryImpl(songDao, songModelMapper));
    }

    @Provides
    @Singleton
    CategoryInteractor getCategoryInteractor(CategoryDao categoryDao, CategoryModelMapper categoryModelMapper) {
        return new CategoryInteractor(new CategoryRepositoryImpl(categoryDao, categoryModelMapper));
    }

    @Provides
    @Singleton
    FavouriteInteractor getFavouriteInteractor(FavouriteDao favouriteDao, SongInteractor songInteractor) {
        return new FavouriteInteractor(new FavouriteRepositoryImpl(favouriteDao, songInteractor));
    }

    @Provides
    @Singleton
    ChordInteractor getChordInteractor(ChordDao chordDao, ChordModelMapper chordModelMapper) {
        return new ChordInteractor(new ChordRepositoryImpl(chordDao, chordModelMapper));
    }
}
