package com.roman.kubik.songer.domain.chord;

public interface ChordRepositoryFactory {

    ChordRepository getChordRepository(@Instruments.Instrument String instrument);
}
