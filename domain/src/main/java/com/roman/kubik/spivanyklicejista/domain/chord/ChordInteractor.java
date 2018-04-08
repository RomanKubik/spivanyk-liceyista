package com.roman.kubik.spivanyklicejista.domain.chord;

import com.roman.kubik.spivanyklicejista.domain.song.Song;
import com.roman.kubik.spivanyklicejista.domain.utils.MarkedChordsRecognizer;

import java.util.List;
import java.util.Set;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by kubik on 1/14/18.
 */

public class ChordInteractor {

    private ChordRepository repository;
    private MarkedChordsRecognizer markedChordsRecognizer;

    public ChordInteractor(ChordRepository repository, MarkedChordsRecognizer markedChordsRecognizer) {
        this.repository = repository;
        this.markedChordsRecognizer = markedChordsRecognizer;
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

    public Single<List<Chord>> getChordsFromSong(Song song) {
        Set<String> chords = markedChordsRecognizer.findSelections(song.getLyrics());
        return Observable.fromIterable(chords)
                .flatMap(s -> repository.getByName(s).toObservable())
                .toList();
    }
}
