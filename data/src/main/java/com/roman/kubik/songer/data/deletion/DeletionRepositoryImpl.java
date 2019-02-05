package com.roman.kubik.songer.data.deletion;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.roman.kubik.songer.data.song.SongModelMapper;
import com.roman.kubik.songer.domain.deletion.Deletion;
import com.roman.kubik.songer.domain.deletion.DeletionRepository;
import com.roman.kubik.songer.domain.song.Song;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class DeletionRepositoryImpl implements DeletionRepository {

    private final DeletionDao deletionDao;
    private final SongModelMapper mapper;

    @Inject
    public DeletionRepositoryImpl(DeletionDao deletionDao, SongModelMapper mapper) {
        this.deletionDao = deletionDao;
        this.mapper = mapper;
    }

    @Override
    public Single<List<Song>> getDeletionList() {
        return deletionDao.getAll().map(s -> Stream.of(s)
                .map(mapper::fromEntity)
                .collect(Collectors.toList()));
    }

    @Override
    public Single<Song> getLastAdded() {
        return deletionDao.getLastDeleted().map(mapper::fromEntity);
    }

    @Override
    public Completable addToDeletion(Song song) {
        return Completable.fromAction(() -> deletionDao.addDeletion(new DeletionEntity(song.getId())));
    }
}
