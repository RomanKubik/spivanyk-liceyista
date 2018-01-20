package com.roman.kubik.spivanyklicejista.domain.favourite;

import com.roman.kubik.spivanyklicejista.domain.song.Song;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by kubik on 1/14/18.
 */

public interface FavouriteRepository {

    Single<List<Song>> getAll();

    Completable add(Song song);

    Completable delete(Song song);
}
