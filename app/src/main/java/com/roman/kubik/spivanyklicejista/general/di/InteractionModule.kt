package com.roman.kubik.spivanyklicejista.general.di

import android.content.Context
import android.preference.PreferenceManager
import com.roman.kubik.spivanyklicejista.data.category.CategoryDao
import com.roman.kubik.spivanyklicejista.data.category.CategoryModelMapper
import com.roman.kubik.spivanyklicejista.data.category.CategoryRepositoryImpl
import com.roman.kubik.spivanyklicejista.data.chord.ChordRepositoryFactoryImpl
import com.roman.kubik.spivanyklicejista.data.chord.GuitarChordRepository
import com.roman.kubik.spivanyklicejista.data.favourite.FavouriteDao
import com.roman.kubik.spivanyklicejista.data.favourite.FavouriteRepositoryImpl
import com.roman.kubik.spivanyklicejista.data.preferences.PreferencesImpl
import com.roman.kubik.spivanyklicejista.data.song.SongDao
import com.roman.kubik.spivanyklicejista.data.song.SongModelMapper
import com.roman.kubik.spivanyklicejista.data.song.SongRepositoryImpl
import com.roman.kubik.spivanyklicejista.domain.category.CategoryInteractor
import com.roman.kubik.spivanyklicejista.domain.chord.ChordInteractor
import com.roman.kubik.spivanyklicejista.domain.favourite.FavouriteInteractor
import com.roman.kubik.spivanyklicejista.domain.preferences.PreferencesInteractor
import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor
import com.roman.kubik.spivanyklicejista.domain.utils.MarkedChordsRecognizer

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
    internal fun getPreferencesInteractor(context: Context): PreferencesInteractor {
        return PreferencesInteractor(PreferencesImpl(PreferenceManager.getDefaultSharedPreferences(context.applicationContext)))
    }

    @Provides
    @Singleton
    internal fun getChordInteractor(chordRepositoryFactoryImpl: ChordRepositoryFactoryImpl, preferencesInteractor: PreferencesInteractor, markedChordsRecognizer: MarkedChordsRecognizer): ChordInteractor {
        return ChordInteractor(chordRepositoryFactoryImpl, preferencesInteractor, markedChordsRecognizer)
    }

}
