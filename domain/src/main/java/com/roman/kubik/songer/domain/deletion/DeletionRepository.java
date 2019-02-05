package com.roman.kubik.songer.domain.deletion;

import com.roman.kubik.songer.domain.song.Song;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface DeletionRepository {

    Single<List<Song>> getDeletionList();

    Single<Song> getLastAdded();

    Completable addToDeletion(Song song);

}
