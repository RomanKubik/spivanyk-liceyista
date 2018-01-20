package com.roman.kubik.spivanyklicejista.domain.chord;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by kubik on 1/14/18.
 */

public interface ChordRepository {

    Single<List<Chord>> getAll();

    Maybe<Chord> getById(int id);

    Maybe<Chord> getByName(String name);

    Completable add(Chord chord);
}
