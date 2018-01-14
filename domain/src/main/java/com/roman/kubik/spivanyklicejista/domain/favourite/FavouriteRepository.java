package com.roman.kubik.spivanyklicejista.domain.favourite;

import com.roman.kubik.spivanyklicejista.domain.song.Song;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by kubik on 1/14/18.
 */

public interface FavouriteRepository {

    Single<List<Song>> getAll();

    void add(Song song);

    void delete(Song song);
}
