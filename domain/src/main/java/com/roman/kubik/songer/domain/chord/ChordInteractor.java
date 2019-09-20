package com.roman.kubik.songer.domain.chord;

import com.roman.kubik.songer.domain.preferences.Preferences;
import com.roman.kubik.songer.domain.preferences.PreferencesInteractor;
import com.roman.kubik.songer.domain.song.Song;
import com.roman.kubik.songer.domain.song.SongRepository;
import com.roman.kubik.songer.domain.utils.ChordsTransposer;
import com.roman.kubik.songer.domain.utils.MarkedChordsRecognizer;

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
    private final ChordRepositoryFactory chordRepositoryFactory;
    private final PreferencesInteractor preferencesInteractor;
    private final MarkedChordsRecognizer markedChordsRecognizer;
    private final ChordsTransposer chordsTransposer;
    private final SongRepository songRepository;

    public ChordInteractor(ChordRepositoryFactory chordRepositoryFactory,
                           PreferencesInteractor preferencesInteractor,
                           MarkedChordsRecognizer markedChordsRecognizer, ChordsTransposer chordsTransposer, SongRepository songRepository) {
        this.chordRepositoryFactory = chordRepositoryFactory;
        this.preferencesInteractor = preferencesInteractor;
        this.markedChordsRecognizer = markedChordsRecognizer;
        this.chordsTransposer = chordsTransposer;
        this.songRepository = songRepository;
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
        Disposable disposable = preferencesInteractor.getPreferences()
                .map(Preferences::getSelectedInstrument)
                .subscribe(i -> repository = chordRepositoryFactory.getChordRepository(i), Throwable::printStackTrace);
    }

    public Single<Song> transposeUp(Song song) {
        return Single.fromCallable(() -> chordsTransposer.transposeUp(song))
                .doOnSuccess(s -> songRepository.insertOrUpdate(song).subscribe());
    }

    public Single<Song> transposeDown(Song song) {
        return Single.fromCallable(() -> chordsTransposer.transposeDown(song))
                .doOnSuccess(s -> songRepository.insertOrUpdate(song).subscribe());
    }
}
