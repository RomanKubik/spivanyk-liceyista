package com.roman.kubik.songer.data.deletion;

import com.roman.kubik.songer.domain.EntityModelMapper;
import com.roman.kubik.songer.domain.deletion.Deletion;

import javax.inject.Inject;

public class DeletionModelMapper implements EntityModelMapper<DeletionEntity, Deletion> {

    @Inject
    public DeletionModelMapper() {
    }

    @Override
    public Deletion fromEntity(DeletionEntity from) {
        return new Deletion(from.getId(), from.getSongId(), from.getTimestamp());
    }

    @Override
    public DeletionEntity toEntity(Deletion from) {
        return new DeletionEntity(from.getId(), from.getSongId(), from.getTimestamp());
    }
}
