package com.roman.kubik.songer.domain.deletion;

import com.roman.kubik.songer.domain.song.Song;

import javax.inject.Inject;

import io.reactivex.Completable;

public class DeletionInteractor {

    private final DeletionRepository deletionRepository;

    @Inject
    public DeletionInteractor(DeletionRepository deletionRepository) {
        this.deletionRepository = deletionRepository;
    }

    public Completable markForDeletion(Song song) {
        return deletionRepository.addToDeletion(song);
    }
}
