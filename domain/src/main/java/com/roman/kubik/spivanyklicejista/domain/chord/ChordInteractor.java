package com.roman.kubik.spivanyklicejista.domain.chord;

import com.roman.kubik.spivanyklicejista.domain.preferences.PreferencesInteractor;
import com.roman.kubik.spivanyklicejista.domain.song.Song;
import com.roman.kubik.spivanyklicejista.domain.utils.MarkedChordsRecognizer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

/**
 * Created by kubik on 1/14/18.
 */

public class ChordInteractor {

    private ChordRepository repository;
    private ChordRepositoryFactory chordRepositoryFactory;
    private PreferencesInteractor preferencesInteractor;
    private MarkedChordsRecognizer markedChordsRecognizer;

    public ChordInteractor(ChordRepositoryFactory chordRepositoryFactory,
                           PreferencesInteractor preferencesInteractor,
                           MarkedChordsRecognizer markedChordsRecognizer) {
        this.chordRepositoryFactory = chordRepositoryFactory;
        this.preferencesInteractor = preferencesInteractor;
        this.markedChordsRecognizer = markedChordsRecognizer;
        updateChordRepository();
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

    public void updateChordRepository() {
        Disposable disposable = preferencesInteractor.selectedInstrument()
                .subscribe(i -> repository = chordRepositoryFactory.getChordRepository(i), Throwable::printStackTrace);
    }

}
