package com.roman.kubik.songer.general.di

import com.roman.kubik.songer.data.category.CategoryDao
import com.roman.kubik.songer.data.category.CategoryModelMapper
import com.roman.kubik.songer.data.category.CategoryRepositoryImpl
import com.roman.kubik.songer.data.favourite.FavouriteDao
import com.roman.kubik.songer.data.favourite.FavouriteRepositoryImpl
import com.roman.kubik.songer.data.history.HistoryDao
import com.roman.kubik.songer.data.history.HistoryRepositoryImpl
import com.roman.kubik.songer.data.song.local.SongDao
import com.roman.kubik.songer.data.song.local.SongModelMapper
import com.roman.kubik.songer.data.song.local.SongRepositoryImpl
import com.roman.kubik.songer.data.song.remote.RemoteSongRepositoryImpl
import com.roman.kubik.songer.data.user.UserRepositoryImpl
import com.roman.kubik.songer.domain.category.CategoryRepository
import com.roman.kubik.songer.domain.favourite.FavouriteRepository
import com.roman.kubik.songer.domain.history.HistoryRepository
import com.roman.kubik.songer.domain.song.RemoteSongRepository
import com.roman.kubik.songer.domain.song.SongRepository
import com.roman.kubik.songer.domain.user.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun getSongRepository(songDao: SongDao, songModelMapper: SongModelMapper): SongRepository = SongRepositoryImpl(songDao, songModelMapper)

    @Provides
    @Singleton
    fun getCategoryRepository(categoryDao: CategoryDao, categoryModelMapper: CategoryModelMapper): CategoryRepository = CategoryRepositoryImpl(categoryDao, categoryModelMapper)

    @Provides
    @Singleton
    fun getFavouriteRepository(favouriteDao: FavouriteDao, songModelMapper: SongModelMapper): FavouriteRepository = FavouriteRepositoryImpl(favouriteDao, songModelMapper)

    @Provides
    @Singleton
    fun getHistoryRepository(historyDao: HistoryDao, songModelMapper: SongModelMapper): HistoryRepository = HistoryRepositoryImpl(historyDao, songModelMapper)

    @Provides
    @Singleton
    fun getUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository = userRepositoryImpl

    @Provides
    @Singleton
    fun getRemoteSongRepository(remoteSongRepository: RemoteSongRepositoryImpl): RemoteSongRepository = remoteSongRepository
}