package com.roman.kubik.spivanyklicejista.domain.favourite;

import com.roman.kubik.spivanyklicejista.domain.song.Song;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by kubik on 1/14/18.
 */

public class FavouriteInteractor {

    private FavouriteRepository favouriteRepository;

    public FavouriteInteractor(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    public Single<List<Song>> getAll() {
        return favouriteRepository.getAll();
    }

    public Single<Boolean> isInFavouriteList(Song song) {
        return favouriteRepository.isInFavourite(song);
    }

    public Completable addSong(Song song) {
        return favouriteRepository.add(song);
    }

    public Completable removeSong(Song song) {
        return favouriteRepository.delete(song);
    }
}
