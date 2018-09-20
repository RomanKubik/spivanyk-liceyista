package com.roman.kubik.songer.domain.song;


import com.roman.kubik.songer.domain.category.Category;
import com.roman.kubik.songer.domain.favourite.FavouriteRepository;
import com.roman.kubik.songer.domain.history.HistoryRepository;

import java.util.List;
import java.util.Random;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by kubik on 1/14/18.
 */

public class SongInteractor {

    private final Random random = new Random();
    private final SongRepository songRepository;
    private final FavouriteRepository favouriteRepository;
    private final HistoryRepository historyRepository;

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

    public Maybe<Song> getById(int id) {
        return songRepository.getById(id);
    }

    public Single<Integer> getCount() {
        return songRepository.getCount();
    }

    public Single<Integer> getCountByCategory(@Category.CategoryId int categoryId) {
        return songRepository.getCountByCategory(categoryId);
    }

    public Completable insertOrUpdate(Song song) {
        return songRepository.insertOrUpdate(song);
    }

    public Completable deleteSong(Song song) {
        return songRepository.delete(song);
    }

    public Single<Song> getRandomSong() {
        return songRepository.getAll()
                .map(l -> l.get(random.nextInt(l.size())));
    }
}
