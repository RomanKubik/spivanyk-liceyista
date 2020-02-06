package com.roman.kubik.songer.data.favourite;

import android.util.Log;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.roman.kubik.songer.data.song.local.SongModelMapper;
import com.roman.kubik.songer.domain.favourite.FavouriteRepository;
import com.roman.kubik.songer.domain.song.Song;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.internal.operators.observable.ObservableAllSingle;

/**
 * Created by kubik on 1/20/18.
 */

public class FavouriteRepositoryImpl implements FavouriteRepository {

    private SongModelMapper mapper;
    private FavouriteDao favouriteDao;

    public FavouriteRepositoryImpl(FavouriteDao favouriteDao, SongModelMapper mapper) {
        this.favouriteDao = favouriteDao;
        this.mapper = mapper;
    }

    @Override
    public Single<List<Song>> getAll() {
        return favouriteDao.getAll()
                .map(s -> Stream.of(s)
                        .map(mapper::fromEntity)
                        .collect(Collectors.toList()));
    }

    @Override
    public Single<List<Song>> search(final String query) {
        return favouriteDao.search("%" + query + "%")
                .map(s -> Stream.of(s)
                        .map(mapper::fromEntity)
                        .collect(Collectors.toList()));
    }

    @Override
    public Single<Boolean> isInFavourite(Song song) {
        Log.d("MyTag", "isInFavourite: " + song.getId());
        return favouriteDao.isSongExists(song.getId())
                .doOnSuccess(s -> Log.d("MyTag", "exists: " + s))
                .doOnError(s -> Log.d("MyTag", "error: " + s.getMessage()))
                .map(s -> true)
                .onErrorResumeNext(t -> ObservableAllSingle.just(false));
    }

    @Override
    public Single<Integer> getCount() {
        return favouriteDao.getCount();
    }

    @Override
    public Completable add(Song song) {
        return Completable.fromAction(() -> favouriteDao.add(new FavouriteEntity(song.getId())));
    }

    @Override
    public Completable delete(Song song) {
        return Completable.fromAction(() -> favouriteDao.delete(song.getId()));
    }
}
