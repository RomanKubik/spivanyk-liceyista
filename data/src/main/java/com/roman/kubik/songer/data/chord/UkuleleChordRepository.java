package com.roman.kubik.songer.data.chord;

import com.roman.kubik.songer.domain.chord.ChordRepository;

import javax.inject.Inject;

public class UkuleleChordRepository implements ChordRepository {

    private static final String FLAT = "b";
    private static final String SHARP = "#";

    @Inject
    public UkuleleChordRepository() {
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
                return "chords/ukulele/A.png";
            case "Am":
                return "chords/ukulele/Am.png";
            case "A7":
                return "chords/ukulele/A7.png";
            case "Am7":
                return "chords/ukulele/Am7.png";
            case "Cb":
            case "H#":
            case "B":
                return "chords/ukulele/B.png";
            case "Cmb":
            case "Hm#":
            case "Bm":
                return "chords/ukulele/Bm.png";
            case "C7b":
            case "H7#":
            case "B7":
                return "chords/ukulele/Bm.png";
            case "Cm7b":
            case "Hm7#":
            case "Bm7":
                return "chords/ukulele/Bm7.png";
            case "B#":
            case "C":
                return "chords/ukulele/C.png";
            case "Bm#":
            case "Cm":
                return "chords/ukulele/Cm.png";
            case "B7#":
            case "C7":
                return "chords/ukulele/C7.png";
            case "Bm7#":
            case "Cm7":
                return "chords/ukulele/Cm7.png";
            case "D":
                return "chords/ukulele/D.png";
            case "Dm":
                return "chords/ukulele/Dm.png";
            case "D7":
                return "chords/ukulele/D7.png";
            case "Dm7":
                return "chords/ukulele/Dm7.png";
            case "Fb":
            case "E":
                return "chords/ukulele/E.png";
            case "Fmb":
            case "Em":
                return "chords/ukulele/Em.png";
            case "F7b":
            case "E7":
                return "chords/ukulele/E7.png";
            case "Fm7b":
            case "Em7":
                return "chords/ukulele/Em7.png";
            case "E#":
            case "F":
                return "chords/ukulele/F.png";
            case "Em#":
            case "Fm":
                return "chords/ukulele/Fm.png";
            case "E7#":
            case "F7":
                return "chords/ukulele/F7.png";
            case "Em7#":
            case "Fm7":
                return "chords/ukulele/Fm7.png";
            case "G":
                return "chords/ukulele/G.png";
            case "Gm":
                return "chords/ukulele/Gm.png";
            case "G7":
                return "chords/ukulele/G7.png";
            case "Gm7":
                return "chords/ukulele/Gm7.png";
            case "G#":
            case "Ab":
                return "chords/ukulele/Ab.png";
            case "Gm#":
            case "Amb":
                return "chords/ukulele/Abm.png";
            case "G7#":
            case "A7b":
                return "chords/ukulele/Ab7.png";
            case "Gm7#":
            case "Am7b":
                return "chords/ukulele/Abm7.png";
            case "A#":
            case "H":
            case "Bb":
                return "chords/ukulele/Bb.png";
            case "A7#":
            case "H7":
            case "B7b":
                return "chords/ukulele/Bb7.png";
            case "Am#":
            case "Hm":
            case "Bmb":
                return "chords/ukulele/Bbm.png";
            case "Am7#":
            case "Hm7":
            case "Bm7b":
                return "chords/ukulele/Bbm7.png";
            case "C#":
            case "Db":
                return "chords/ukulele/Db.png";
            case "C7#":
            case "Db7":
                return "chords/ukulele/Db7.png";
            case "Cm#":
            case "Dmb":
                return "chords/ukulele/Dbm.png";
            case "Cm7#":
            case "Dm7b":
                return "chords/ukulele/Dbm7.png";
            case "D#":
            case "Eb":
                return "chords/ukulele/Eb.png";
            case "D7#":
            case "E7b":
                return "chords/ukulele/Eb7.png";
            case "Dm#":
            case "Emb":
                return "chords/ukulele/Ebm.png";
            case "Dm7#":
            case "Em7b":
                return "chords/ukulele/Ebm7.png";
            case "Gb":
            case "F#":
                return "chords/ukulele/F#.png";
            case "G7b":
            case "F7#":
                return "chords/ukulele/F#7.png";
            case "Gmb":
            case "Fm#":
                return "chords/ukulele/F#m.png";
            case "Gm7b":
            case "Fm7#":
                return "chords/ukulele/F#m7.png";
        }
        return "";
    }
}
