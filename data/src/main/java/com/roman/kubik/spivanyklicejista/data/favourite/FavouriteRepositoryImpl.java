package com.roman.kubik.spivanyklicejista.data.favourite;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.roman.kubik.spivanyklicejista.domain.favourite.FavouriteRepository;
import com.roman.kubik.spivanyklicejista.domain.song.Song;
import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor;
import com.roman.kubik.spivanyklicejista.domain.song.SongRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by kubik on 1/20/18.
 */

public class FavouriteRepositoryImpl implements FavouriteRepository {

    private SongInteractor songInteractor;
    private FavouriteDao favouriteDao;

    public FavouriteRepositoryImpl(FavouriteDao favouriteDao, SongInteractor songInteractor) {
        this.favouriteDao = favouriteDao;
        this.songInteractor = songInteractor;
    }

    @Override
    public Single<List<Song>> getAll() {
        return favouriteDao.getAll()
                .map(f -> Stream.of(f)
                        .map(f1 -> songInteractor.getById(f1.getSongId())
                                .blockingGet())
                        .collect(Collectors.toList()));
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
