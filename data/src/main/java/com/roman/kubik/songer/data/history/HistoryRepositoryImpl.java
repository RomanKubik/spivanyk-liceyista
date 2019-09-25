package com.roman.kubik.songer.data.history;

import com.roman.kubik.songer.data.song.SongModelMapper;
import com.roman.kubik.songer.domain.history.HistoryRepository;
import com.roman.kubik.songer.domain.song.Song;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class HistoryRepositoryImpl implements HistoryRepository {

    private HistoryDao historyDao;
    private SongModelMapper mapper;

    @Inject
    public HistoryRepositoryImpl(HistoryDao historyDao, SongModelMapper mapper) {
        this.historyDao = historyDao;
        this.mapper = mapper;
    }

    @Override
    public Single<List<Song>> getLastSongs() {
        return historyDao.getHistory()
                .toObservable()
                .flatMapIterable(l -> l)
                .map(mapper::fromEntity)
                .toList();
    }

    @Override
    public Single<List<Song>> search(final String query) {
        return historyDao.search("%" + query + "%")
                .toObservable()
                .flatMapIterable(l -> l)
                .map(mapper::fromEntity)
                .toList();
    }

    @Override
    public Completable addSongToHistory(Song song) {
        return Completable
                .fromAction(() -> historyDao.addToHistory(new HistoryEntity(song.getId())));
    }
}
