package com.roman.kubik.songer.domain.favourite;

import com.roman.kubik.songer.domain.song.Song;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by kubik on 1/14/18.
 */

public interface FavouriteRepository {

    Single<List<Song>> getAll();

    Single<Boolean> isInFavourite(Song song);

    Single<Integer> getCount();

    Completable add(Song song);

    Completable delete(Song song);
}
