package com.roman.kubik.songer.domain.history;

import com.roman.kubik.songer.domain.song.Song;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class HistoryInteractor {

    private HistoryRepository historyRepository;

    @Inject
    public HistoryInteractor(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public Single<List<Song>> getLastPlayed() {
        return historyRepository.getLastSongs();
    }

    public Completable addSong(Song song) {
        return historyRepository.addSongToHistory(song);
    }
}
