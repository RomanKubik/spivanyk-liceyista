package com.roman.kubik.spivanyklicejista.domain.chord;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by kubik on 1/14/18.
 */

public class ChordInteractor {

    private ChordRepository repository;

    public ChordInteractor(ChordRepository repository) {
        this.repository = repository;
    }

    public Single<List<Chord>> getAll() {
        return repository.getAll();
    }

    public Maybe<Chord> getById(int id) {
        return repository.getById(id);
    }

    public Maybe<Chord> getByName(String name) {
        return repository.getByName(name);
    }

    public void add(Chord chord) {
        repository.add(chord);
    }
}
