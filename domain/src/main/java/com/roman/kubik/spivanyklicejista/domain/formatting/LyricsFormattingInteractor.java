package com.roman.kubik.spivanyklicejista.domain.formatting;

public class LyricsFormattingInteractor {

    private LyricsFormatter chordsRecognizer;
    private LyricsFormatter chordsRemover;
    private ChordsMarker chordsCreator;

    public LyricsFormattingInteractor(LyricsFormatter chordsRecognizer, LyricsFormatter chordsRemover, ChordsMarker chordsCreator) {
        this.chordsRecognizer = chordsRecognizer;
        this.chordsRemover = chordsRemover;
        this.chordsCreator = chordsCreator;
    }

    public CharSequence recognizeChords(String text) {
        return chordsRecognizer.format(text);
    }

    public CharSequence removeChords(String text) {
        return chordsRemover.format(text);
    }

    public CharSequence createChords(String text, OnChordClickListener onChordClickListener, int textColor, int backgroundColor) {
        return chordsCreator.format(text, onChordClickListener, textColor, backgroundColor);
    }
}
