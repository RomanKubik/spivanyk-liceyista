package com.roman.kubik.spivanyklicejista.general.di

import com.roman.kubik.spivanyklicejista.data.category.CategoryDao
import com.roman.kubik.spivanyklicejista.data.category.CategoryModelMapper
import com.roman.kubik.spivanyklicejista.data.category.CategoryRepositoryImpl
import com.roman.kubik.spivanyklicejista.data.chord.ChordDao
import com.roman.kubik.spivanyklicejista.data.chord.ChordModelMapper
import com.roman.kubik.spivanyklicejista.data.chord.ChordRepositoryImpl
import com.roman.kubik.spivanyklicejista.data.favourite.FavouriteDao
import com.roman.kubik.spivanyklicejista.data.favourite.FavouriteRepositoryImpl
import com.roman.kubik.spivanyklicejista.data.song.SongDao
import com.roman.kubik.spivanyklicejista.data.song.SongModelMapper
import com.roman.kubik.spivanyklicejista.data.song.SongRepositoryImpl
import com.roman.kubik.spivanyklicejista.domain.category.CategoryInteractor
import com.roman.kubik.spivanyklicejista.domain.chord.ChordInteractor
import com.roman.kubik.spivanyklicejista.domain.favourite.FavouriteInteractor
import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Created by kubik on 1/20/18.
 */

@Module
class InteractionModule {

    @Provides
    @Singleton
    internal fun getSongInteractor(songDao: SongDao, songModelMapper: SongModelMapper): SongInteractor {
        return SongInteractor(SongRepositoryImpl(songDao, songModelMapper))
    }

    @Provides
    @Singleton
    internal fun getCategoryInteractor(categoryDao: CategoryDao, categoryModelMapper: CategoryModelMapper): CategoryInteractor {
        return CategoryInteractor(CategoryRepositoryImpl(categoryDao, categoryModelMapper))
    }

    @Provides
    @Singleton
    internal fun getFavouriteInteractor(favouriteDao: FavouriteDao, songInteractor: SongInteractor): FavouriteInteractor {
        return FavouriteInteractor(FavouriteRepositoryImpl(favouriteDao, songInteractor))
    }

    @Provides
    @Singleton
    internal fun getChordInteractor(chordDao: ChordDao, chordModelMapper: ChordModelMapper): ChordInteractor {
        return ChordInteractor(ChordRepositoryImpl(chordDao, chordModelMapper))
    }
}
