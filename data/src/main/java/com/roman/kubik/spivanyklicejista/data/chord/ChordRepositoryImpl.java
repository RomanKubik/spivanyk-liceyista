package com.roman.kubik.spivanyklicejista.data.chord;

import android.util.Log;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.roman.kubik.spivanyklicejista.domain.chord.Chord;
import com.roman.kubik.spivanyklicejista.domain.chord.ChordRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by kubik on 1/20/18.
 */

public class ChordRepositoryImpl implements ChordRepository {

    private ChordDao chordDao;
    private ChordModelMapper chordModelMapper;

    public ChordRepositoryImpl(ChordDao chordDao, ChordModelMapper chordModelMapper) {
        this.chordDao = chordDao;
        this.chordModelMapper = chordModelMapper;
    }

    @Override
    public Single<List<Chord>> getAll() {
        return chordDao.getAll()
                .map(c -> Stream.of(c)
                        .map(c1 -> chordModelMapper.fromEntity(c1))
                        .collect(Collectors.toList()));
    }

    @Override
    public Maybe<Chord> getById(int id) {
        return chordDao.getById(id)
                .map(c -> chordModelMapper.fromEntity(c));
    }

    @Override
    public Maybe<Chord> getByName(String name) {
        Log.d("MyTag", "getByName: " + name);
        return chordDao.getByName(name)
                .map(c -> chordModelMapper.fromEntity(c))
                .doOnSuccess(c -> Log.d("MyTag", "getByName: " + c.getName()));
    }

    @Override
    public Completable add(Chord chord) {
        return Completable.fromAction(() -> chordDao.add(chordModelMapper.toEntity(chord)));
    }
}
