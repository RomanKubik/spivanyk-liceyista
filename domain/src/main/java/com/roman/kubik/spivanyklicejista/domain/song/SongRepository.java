package com.roman.kubik.spivanyklicejista.domain.song;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by kubik on 1/14/18.
 */

public interface SongRepository {
    Single<List<Song>> getAll();

    Single<List<Song>> getAllByCategory(int categoryId);

    Single<List<Song>> search(String text);

    Maybe<Song> getById(int id);

    Single<Integer> getCount();

    Single<Integer> getCountByCategory(int categoryId);

    Completable insertOrUpdate(Song song);

    Completable delete(Song song);
}
