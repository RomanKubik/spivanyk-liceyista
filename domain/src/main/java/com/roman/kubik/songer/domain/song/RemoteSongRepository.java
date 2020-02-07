package com.roman.kubik.songer.domain.song;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

public interface RemoteSongRepository {
    Single<List<Song>> getAll();

    Single<List<Song>> search(String query);

    Single<Song> getSong(String id);
}
