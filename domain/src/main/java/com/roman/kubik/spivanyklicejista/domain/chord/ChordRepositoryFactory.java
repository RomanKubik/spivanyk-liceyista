package com.roman.kubik.spivanyklicejista.domain.chord;

public interface ChordRepositoryFactory {

    ChordRepository getChordRepository(String instrument);
}
