package com.roman.kubik.spivanyklicejista.data.chord;

import com.roman.kubik.spivanyklicejista.domain.chord.ChordRepository;

import javax.inject.Inject;

/**
 * Created by kubik on 1/20/18.
 */

public class GuitarChordRepository implements ChordRepository {

    private static final String FLAT = "b";
    private static final String SHARP = "#";

    @Inject
    public GuitarChordRepository() {
    }

    @Override
    public String getChordImagePath(String chord) {
        chord = unifyChord(chord);
        return getChord(chord);
    }

    private String unifyChord(String chord) {
        chord = chord.replaceAll("\\s*", "");
        if (chord.contains(FLAT)) {
            chord = chord.replace(FLAT, "");
            chord = chord.concat(FLAT);
        }
        if (chord.contains(SHARP)) {
            chord = chord.replace(SHARP, "");
            chord = chord.concat(SHARP);
        }
        return chord;
    }

    private String getChord(String chord) {
        switch (chord) {
            case "A":
                return "chords/guitar/A.png";
            case "Am":
                return "chords/guitar/Am.png";
            case "A7":
                return "chords/guitar/A7.png";
            case "Am7":
                return "chords/guitar/Am7.png";
            case "Cb":
            case "H#":
            case "B":
                return "chords/guitar/B.png";
            case "Cmb":
            case "Hm#":
            case "Bm":
                return "chords/guitar/Bm.png";
            case "C7b":
            case "H7#":
            case "B7":
                return "chords/guitar/Bm.png";
            case "Cm7b":
            case "Hm7#":
            case "Bm7":
                return "chords/guitar/Bm7.png";
            case "B#":
            case "C":
                return "chords/guitar/C.png";
            case "Bm#":
            case "Cm":
                return "chords/guitar/Cm.png";
            case "B7#":
            case "C7":
                return "chords/guitar/C7.png";
            case "Bm7#":
            case "Cm7":
                return "chords/guitar/Cm7.png";
            case "D":
                return "chords/guitar/D.png";
            case "Dm":
                return "chords/guitar/Dm.png";
            case "D7":
                return "chords/guitar/D7.png";
            case "Dm7":
                return "chords/guitar/Dm7.png";
            case "Fb":
            case "E":
                return "chords/guitar/E.png";
            case "Fmb":
            case "Em":
                return "chords/guitar/Em.png";
            case "F7b":
            case "E7":
                return "chords/guitar/E7.png";
            case "Fm7b":
            case "Em7":
                return "chords/guitar/Em7.png";
            case "E#":
            case "F":
                return "chords/guitar/F.png";
            case "Em#":
            case "Fm":
                return "chords/guitar/Fm.png";
            case "E7#":
            case "F7":
                return "chords/guitar/F7.png";
            case "Em7#":
            case "Fm7":
                return "chords/guitar/Fm7.png";
            case "G":
                return "chords/guitar/G.png";
            case "Gm":
                return "chords/guitar/Gm.png";
            case "G7":
                return "chords/guitar/G7.png";
            case "Gm7":
                return "chords/guitar/Gm7.png";
            case "G#":
            case "Ab":
                return "chords/guitar/Ab.png";
            case "Gm#":
            case "Amb":
                return "chords/guitar/Abm.png";
            case "G7#":
            case "A7b":
                return "chords/guitar/Ab7.png";
            case "Gm7#":
            case "Am7b":
                return "chords/guitar/Abm7.png";
            case "A#":
            case "H":
            case "Bb":
                return "chords/guitar/Bb.png";
            case "A7#":
            case "H7":
            case "B7b":
                return "chords/guitar/Bb7.png";
            case "Am#":
            case "Hm":
            case "Bmb":
                return "chords/guitar/Bbm.png";
            case "Am7#":
            case "Hm7":
            case "Bm7b":
                return "chords/guitar/Bbm7.png";
            case "C#":
            case "Db":
                return "chords/guitar/Db.png";
            case "C7#":
            case "Db7":
                return "chords/guitar/Db7.png";
            case "Cm#":
            case "Dmb":
                return "chords/guitar/Dbm.png";
            case "Cm7#":
            case "Dm7b":
                return "chords/guitar/Dbm7.png";
            case "D#":
            case "Eb":
                return "chords/guitar/Eb.png";
            case "D7#":
            case "E7b":
                return "chords/guitar/Eb7.png";
            case "Dm#":
            case "Emb":
                return "chords/guitar/Ebm.png";
            case "Dm7#":
            case "Em7b":
                return "chords/guitar/Ebm7.png";
            case "Gb":
            case "F#":
                return "chords/guitar/F#.png";
            case "G7b":
            case "F7#":
                return "chords/guitar/F#7.png";
            case "Gmb":
            case "Fm#":
                return "chords/guitar/F#m.png";
            case "Gm7b":
            case "Fm7#":
                return "chords/guitar/F#m7.png";
        }
        return "";
    }

}
