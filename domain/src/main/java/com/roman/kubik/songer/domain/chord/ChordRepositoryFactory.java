package com.roman.kubik.songer.domain.chord;

import com.roman.kubik.songer.domain.preferences.Preferences;

public interface ChordRepositoryFactory {

    ChordRepository getChordRepository(@Preferences.Instrument String instrument);
}
