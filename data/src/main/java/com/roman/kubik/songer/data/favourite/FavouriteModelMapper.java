package com.roman.kubik.songer.data.favourite;

import com.roman.kubik.songer.domain.EntityModelMapper;
import com.roman.kubik.songer.domain.favourite.Favourite;

/**
 * Created by kubik on 1/20/18.
 */

public class FavouriteModelMapper implements EntityModelMapper<FavouriteEntity, Favourite> {
    @Override
    public Favourite fromEntity(FavouriteEntity from) {
        return new Favourite(from.getId(), from.getSongId());
    }

    @Override
    public FavouriteEntity toEntity(Favourite from) {
        return new FavouriteEntity(from.getId(), from.getSongId());
    }
}
