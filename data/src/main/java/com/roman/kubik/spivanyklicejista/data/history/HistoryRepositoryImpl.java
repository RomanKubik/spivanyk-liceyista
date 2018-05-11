package com.roman.kubik.spivanyklicejista.data.history;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.roman.kubik.spivanyklicejista.domain.history.HistoryRepository;
import com.roman.kubik.spivanyklicejista.domain.song.Song;
import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class HistoryRepositoryImpl implements HistoryRepository {

    private HistoryDao historyDao;
    private SongInteractor songInteractor;

    @Inject
    public HistoryRepositoryImpl(HistoryDao historyDao, SongInteractor songInteractor) {
        this.historyDao = historyDao;
        this.songInteractor = songInteractor;
    }

    @Override
    public Single<List<Song>> getLastSongs() {
        return historyDao.getHistory()
                .map(l -> Stream.of(l)
                        .map(f1 -> songInteractor.getById(f1.getSongId())
                                .blockingGet())
                        .collect(Collectors.toList()));
    }

    @Override
    public Completable addSongToHistory(Song song) {
        return Completable.fromAction(() -> historyDao.addToHistory(new HistoryEntity(song.getId())));
    }
}
