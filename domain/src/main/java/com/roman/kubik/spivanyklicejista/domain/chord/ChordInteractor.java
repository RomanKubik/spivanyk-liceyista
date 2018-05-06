package com.roman.kubik.spivanyklicejista.domain.chord;

import com.roman.kubik.spivanyklicejista.domain.song.Song;
import com.roman.kubik.spivanyklicejista.domain.utils.MarkedChordsRecognizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

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

    public Single<List<Chord>> getChordsFromSong(Song song) {
        return Observable.fromCallable(()
                -> new ArrayList<>(markedChordsRecognizer.findSelections(song.getLyrics())))
                .flatMapIterable(l -> l)
                .map(c -> new Chord(c, getChordPath(c)))
                .toList();
    }

    public String getChordPath(String chord) {
        return repository.getChordImagePath(chord);
    }
}
