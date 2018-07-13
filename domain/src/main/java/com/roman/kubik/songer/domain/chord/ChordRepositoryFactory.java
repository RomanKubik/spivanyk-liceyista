package com.roman.kubik.songer.domain.chord;

public interface ChordRepositoryFactory {

    ChordRepository getChordRepository(String instrument);
}
