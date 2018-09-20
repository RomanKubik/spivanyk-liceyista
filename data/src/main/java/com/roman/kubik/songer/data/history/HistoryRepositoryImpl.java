package com.roman.kubik.songer.data.history;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.roman.kubik.songer.data.song.SongModelMapper;
import com.roman.kubik.songer.domain.history.HistoryRepository;
import com.roman.kubik.songer.domain.song.Song;
import com.roman.kubik.songer.domain.song.SongInteractor;

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
                .map(l -> Stream.of(l)
                        .map(mapper::fromEntity)
                        .collect(Collectors.toList()));
    }

    @Override
    public Completable addSongToHistory(Song song) {
        return Completable
                .fromAction(() -> historyDao.addToHistory(new HistoryEntity(song.getId())));
    }
}
