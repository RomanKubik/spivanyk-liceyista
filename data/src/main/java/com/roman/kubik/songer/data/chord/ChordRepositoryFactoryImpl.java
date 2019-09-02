package com.roman.kubik.songer.data.chord;

import com.roman.kubik.songer.domain.chord.ChordRepository;
import com.roman.kubik.songer.domain.chord.ChordRepositoryFactory;
import com.roman.kubik.songer.domain.chord.Instruments;

import javax.inject.Inject;

import static com.roman.kubik.songer.domain.chord.Instruments.UKULELE;

public class ChordRepositoryFactoryImpl implements ChordRepositoryFactory {

    @Inject
    public ChordRepositoryFactoryImpl() {
    }

    @Override
    public ChordRepository getChordRepository(@Instruments.Instrument String instrument) {
        if (UKULELE.equals(instrument)) {
            return new UkuleleChordRepository();
        }
        return new GuitarChordRepository();
    }
}
