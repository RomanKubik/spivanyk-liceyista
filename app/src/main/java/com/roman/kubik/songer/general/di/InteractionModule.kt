package com.roman.kubik.songer.general.di

import android.content.Context
import android.preference.PreferenceManager
import com.roman.kubik.songer.data.chord.ChordRepositoryFactoryImpl
import com.roman.kubik.songer.data.logger.FirebaseLogger
import com.roman.kubik.songer.data.preferences.PreferencesImpl
import com.roman.kubik.songer.domain.category.CategoryInteractor
import com.roman.kubik.songer.domain.category.CategoryRepository
import com.roman.kubik.songer.domain.chord.ChordInteractor
import com.roman.kubik.songer.domain.favourite.FavouriteInteractor
import com.roman.kubik.songer.domain.favourite.FavouriteRepository
import com.roman.kubik.songer.domain.history.HistoryInteractor
import com.roman.kubik.songer.domain.history.HistoryRepository
import com.roman.kubik.songer.domain.logger.LoggerInteractor
import com.roman.kubik.songer.domain.navigation.NavigationInteractor
import com.roman.kubik.songer.domain.preferences.PreferencesInteractor
import com.roman.kubik.songer.domain.shaker.ShakeDetector
import com.roman.kubik.songer.domain.shaker.ShakeInteractor
import com.roman.kubik.songer.domain.song.SongInteractor
import com.roman.kubik.songer.domain.song.SongRepository
import com.roman.kubik.songer.domain.utils.MarkedChordsRecognizer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by kubik on 1/20/18.
 */

@Module
class InteractionModule {

    @Provides
    @Singleton
    internal fun getSongInteractor(songRepository: SongRepository, favouriteRepository: FavouriteRepository, historyRepository: HistoryRepository): SongInteractor {
        return SongInteractor(songRepository, favouriteRepository, historyRepository)
    }

    @Provides
    @Singleton
    internal fun getCategoryInteractor(categoryRepository: CategoryRepository): CategoryInteractor {
        return CategoryInteractor(categoryRepository)
    }

    @Provides
    @Singleton
    internal fun getFavouriteInteractor(favouriteRepository: FavouriteRepository): FavouriteInteractor {
        return FavouriteInteractor(favouriteRepository)
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

    @Provides
    @Singleton
    internal fun getHistoryInteractor(historyRepository: HistoryRepository): HistoryInteractor {
        return HistoryInteractor(historyRepository)
    }

    @Provides
    @Singleton
    internal fun getLoggerInteractor(firebaseLogger: FirebaseLogger): LoggerInteractor = LoggerInteractor(firebaseLogger)

    @Provides
    @Singleton
    internal fun getShakerInteractor(shakeDetector: ShakeDetector) = ShakeInteractor(shakeDetector)

}
