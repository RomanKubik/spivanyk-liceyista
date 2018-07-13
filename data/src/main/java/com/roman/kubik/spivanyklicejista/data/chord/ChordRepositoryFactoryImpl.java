package com.roman.kubik.spivanyklicejista.data.chord;

import com.roman.kubik.spivanyklicejista.domain.chord.ChordRepository;
import com.roman.kubik.spivanyklicejista.domain.chord.ChordRepositoryFactory;

import javax.inject.Inject;

public class ChordRepositoryFactoryImpl implements ChordRepositoryFactory {

    @Inject
    public ChordRepositoryFactoryImpl() {
    }

    @Override
    public ChordRepository getChordRepository(String instrument) {
        switch (instrument) {
            case Instruments.GUITAR:
                return new GuitarChordRepository();
            case Instruments.UKULELE:
                return new UkuleleChordRepository();
            default:
                return new GuitarChordRepository();
        }
    }
}
