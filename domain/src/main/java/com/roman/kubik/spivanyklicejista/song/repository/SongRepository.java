package com.roman.kubik.spivanyklicejista.song.repository;

import com.roman.kubik.spivanyklicejista.song.model.Song;

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

    Completable insertOrUpdate(Song song);

    Completable delete(Song song);
}
