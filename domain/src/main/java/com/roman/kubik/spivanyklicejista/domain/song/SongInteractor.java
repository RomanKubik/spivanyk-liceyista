package com.roman.kubik.spivanyklicejista.domain.song;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by kubik on 1/14/18.
 */

public class SongInteractor {

    private SongRepository songRepository;

    public SongInteractor(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Single<List<Song>> getAll() {
        return songRepository.getAll();
    }

    public Single<List<Song>> getAllByCategory(int categoryId) {
        return songRepository.getAllByCategory(categoryId);
    }

    public Single<List<Song>> search(String text) {
        return songRepository.search(text);
    }

    public Maybe<Song> getById(int id) {
        return songRepository.getById(id);
    }

    public Single<Integer> getCount() {
        return songRepository.getCount();
    }

    public Single<Integer> getCountByCategory(int categoryId) {
        return songRepository.getCountByCategory(categoryId);
    }

    public Completable addSong(Song song) {
        return songRepository.insertOrUpdate(song);
    }

    public Completable updateSong(Song song) {
        return songRepository.insertOrUpdate(song);
    }

    public Completable deleteSong(Song song) {
        return songRepository.delete(song);
    }
}
