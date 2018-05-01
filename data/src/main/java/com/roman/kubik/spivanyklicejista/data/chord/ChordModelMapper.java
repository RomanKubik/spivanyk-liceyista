package com.roman.kubik.spivanyklicejista.data.chord;

import com.roman.kubik.spivanyklicejista.domain.EntityModelMapper;
import com.roman.kubik.spivanyklicejista.domain.chord.Chord;

/**
 * Created by kubik on 1/20/18.
 */

public class ChordModelMapper implements EntityModelMapper<ChordEntity, Chord> {
    @Override
    public Chord fromEntity(ChordEntity from) {
        return new Chord(from.getId(), from.getName(), from.getImagePath().replace("guitar", "ukulele"), from.getSoundPath());
    }

    @Override
    public ChordEntity toEntity(Chord from) {
        return new ChordEntity(from.getId(), from.getName(), from.getImagePath(), from.getSoundPath());
    }
}
