package com.roman.kubik.songer.domain.song;


import com.roman.kubik.songer.domain.category.Category;
import com.roman.kubik.songer.domain.favourite.FavouriteRepository;
import com.roman.kubik.songer.domain.formatting.LyricsFormatter;
import com.roman.kubik.songer.domain.history.HistoryRepository;
import com.roman.kubik.songer.domain.utils.TextUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by kubik on 1/14/18.
 */

public class SongInteractor {

    private final SongRepository localSongRepository;
    private final RemoteSongRepository remoteSongRepository;
    private final FavouriteRepository favouriteRepository;
    private final HistoryRepository historyRepository;
    private final LyricsFormatter chordsRecognizer;
    private Song deletion;

    @Inject
    public SongInteractor(final SongRepository localSongRepository,
                          final RemoteSongRepository remoteSongRepository,
                          final FavouriteRepository favouriteRepository,
                          final HistoryRepository historyRepository,
                          final LyricsFormatter chordsRecognizer) {
        this.localSongRepository = localSongRepository;
        this.remoteSongRepository = remoteSongRepository;
        this.favouriteRepository = favouriteRepository;
        this.historyRepository = historyRepository;
        this.chordsRecognizer = chordsRecognizer;
    }

    public Single<List<Song>> getAllByCategory(@Category.CategoryId int categoryId) {
        switch (categoryId) {
            case Category.FAVOURITE_ID:
                return favouriteRepository.getAll();
            case Category.LAST_ID:
                return historyRepository.getLastSongs();
            case Category.ALL_ID:
                return localSongRepository.getAll();
            case Category.WEB_ID:
                return remoteSongRepository.getAll();
            case Category.ABROAD_ID:
            case Category.BONFIRE_ID:
            case Category.PATRIOTIC_ID:
            case Category.USERS_ID:
            default:
                return localSongRepository.getAllByCategory(categoryId);
        }
    }

    public Single<List<Song>> search(String query, @Category.CategoryId int categoryId) {
        switch (categoryId) {
            case Category.FAVOURITE_ID:
                return favouriteRepository.search(query);
            case Category.LAST_ID:
                return historyRepository.search(query);
            case Category.ALL_ID:
                return localSongRepository.search(query);
            case Category.WEB_ID:
                return remoteSongRepository.search(query);
            case Category.ABROAD_ID:
            case Category.BONFIRE_ID:
            case Category.PATRIOTIC_ID:
            case Category.USERS_ID:
            default:
                return localSongRepository.search(query, categoryId);
        }
    }

    public Single<Song> getById(String id) {
        return localSongRepository.getById(id)
                .onErrorResumeNext(
                        remoteSongRepository.getSong(id)
                                .doOnSuccess(s -> s.setLyrics(chordsRecognizer.format(s.getLyrics()).toString()))
                );
    }

    public Single<Integer> getCount() {
        return localSongRepository.getCount();
    }

    public Single<Integer> getCountByCategory(@Category.CategoryId int categoryId) {
        return localSongRepository.getCountByCategory(categoryId);
    }

    public Completable insertOrUpdate(Song song) {
        if (TextUtils.isEmpty(song.getTitle())) {
            return Completable.error(new IllegalArgumentException("Title must be not empty"));
        }
        if (TextUtils.isEmpty(song.getLyrics())) {
            return Completable.error(new IllegalArgumentException("Lyrics must be not empty"));
        }
        return localSongRepository.insertOrUpdate(song);
    }

    public Completable deleteSong(Song song) {
        deletion = song;
        return localSongRepository.delete(song);
    }

    public Completable undoDeletion() {
        if (deletion != null) {
            return localSongRepository.insertOrUpdate(deletion).doOnComplete(() -> deletion = null);
        }
        return Completable.complete();
    }

}
