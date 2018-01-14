package com.roman.kubik.spivanyklicejista.data.song;


import com.roman.kubik.spivanyklicejista.domain.EntityModelMapper;
import com.roman.kubik.spivanyklicejista.domain.song.model.Song;

/**
 * Data mapper used to map song entity to model classes and vice versa.
 * Created by kubik on 1/14/18.
 */

public class SongModelMapper implements EntityModelMapper<SongEntity, Song> {
    @Override
    public Song fromEntity(SongEntity from) {
        return new Song(from.getId(), from.getTitle(), from.getLyrics(), from.getCategoryId());
    }

    @Override
    public SongEntity toEntity(Song from) {
        return new SongEntity(from.getId(), from.getTitle(), from.getLyrics(), from.getCategoryId());
    }
}
