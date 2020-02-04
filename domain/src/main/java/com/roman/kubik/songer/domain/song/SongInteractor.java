package com.roman.kubik.songer.domain.song;


import com.roman.kubik.songer.domain.category.Category;
import com.roman.kubik.songer.domain.favourite.FavouriteRepository;
import com.roman.kubik.songer.domain.history.HistoryRepository;
import com.roman.kubik.songer.domain.utils.TextUtils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kubik on 1/14/18.
 */

public class SongInteractor {

    private final SongRepository songRepository;
    private final FavouriteRepository favouriteRepository;
    private final HistoryRepository historyRepository;
    private Song deletion;

    public SongInteractor(SongRepository songRepository, FavouriteRepository favouriteRepository,
                          final HistoryRepository historyRepository) {
        this.songRepository = songRepository;
        this.favouriteRepository = favouriteRepository;
        this.historyRepository = historyRepository;
    }

    public Single<List<Song>> getAllByCategory(@Category.CategoryId int categoryId) {
        switch (categoryId) {
            case Category.FAVOURITE_ID:
                return favouriteRepository.getAll();
            case Category.LAST_ID:
                return historyRepository.getLastSongs();
            case Category.ALL_ID:
                return songRepository.getAll();
            case Category.ABROAD_ID:
            case Category.BONFIRE_ID:
            case Category.PATRIOTIC_ID:
            case Category.USERS_ID:
            default:
                return songRepository.getAllByCategory(categoryId);
        }
    }

    public Single<List<Song>> search(String query, @Category.CategoryId int categoryId) {
        switch (categoryId) {
            case Category.FAVOURITE_ID:
                return favouriteRepository.search(query);
            case Category.LAST_ID:
                return historyRepository.search(query);
            case Category.ALL_ID:
                return songRepository.search(query);
            case Category.ABROAD_ID:
            case Category.BONFIRE_ID:
            case Category.PATRIOTIC_ID:
            case Category.USERS_ID:
            default:
                return songRepository.search(query, categoryId);
        }
    }

    public Maybe<Song> getById(String id) {
        return songRepository.getById(id);
    }

    public Single<Integer> getCount() {
        return songRepository.getCount();
    }

    public Single<Integer> getCountByCategory(@Category.CategoryId int categoryId) {
        return songRepository.getCountByCategory(categoryId);
    }

    public Completable insertOrUpdate(Song song) {
        if (TextUtils.isEmpty(song.getTitle())) {
            return Completable.error(new IllegalArgumentException("Title must be not empty"));
        }
        if (TextUtils.isEmpty(song.getLyrics())) {
            return Completable.error(new IllegalArgumentException("Lyrics must be not empty"));
        }
        return songRepository.insertOrUpdate(song);
    }

    public Completable deleteSong(Song song) {
        deletion = song;
        return songRepository.delete(song);
    }

    public Completable undoDeletion() {
        if (deletion != null){
            return songRepository.insertOrUpdate(deletion).doOnComplete(() -> deletion = null);
        }
        return Completable.complete();
    }

}
