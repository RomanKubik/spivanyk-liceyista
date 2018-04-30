package com.roman.kubik.spivanyklicejista.domain.formatting;

public interface ChordsMarker {

    CharSequence format(String text, OnChordClickListener onChordClickListener, int textColor, int backGroundColor);
}
