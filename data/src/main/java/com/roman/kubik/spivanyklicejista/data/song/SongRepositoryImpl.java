package com.roman.kubik.spivanyklicejista.data.song;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.roman.kubik.spivanyklicejista.domain.song.Song;
import com.roman.kubik.spivanyklicejista.domain.song.SongRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Room implementation of {@link SongRepository}.
 * Created by kubik on 1/14/18.
 */

public class SongRepositoryImpl implements SongRepository {

    private SongDao songDao;
    private SongModelMapper songModelMapper;

    public SongRepositoryImpl(SongDao songDao, SongModelMapper songModelMapper) {
        this.songDao = songDao;
        this.songModelMapper = songModelMapper;
    }

    @Override
    public Single<List<Song>> getAll() {
        return songDao.getAll()
                .map(s -> Stream.of(s)
                        .map(s1 -> songModelMapper.fromEntity(s1)).
                                collect(Collectors.toList()));
    }

    @Override
    public Single<List<Song>> getAllByCategory(int categoryId) {
        return songDao.getAllByCategory(categoryId)
                .map(s -> Stream.of(s)
                        .map(s1 -> songModelMapper.fromEntity(s1)).
                                collect(Collectors.toList()));
    }

    @Override
    public Single<List<Song>> search(String text) {
        return songDao.search("%" + text + "%")
                .map(s -> Stream.of(s)
                        .map(s1 -> songModelMapper.fromEntity(s1)).
                                collect(Collectors.toList()));
    }

    @Override
    public Maybe<Song> getById(int id) {
        return songDao.getById(id).map(s -> songModelMapper.fromEntity(s));
    }

    @Override
    public Completable insertOrUpdate(Song song) {
        return Completable.fromAction(() -> songDao.insertOrUpdate(songModelMapper.toEntity(song)));
    }

    @Override
    public Completable delete(Song song) {
        return Completable.fromAction(() -> songDao.delete(songModelMapper.toEntity(song)));
    }
}
