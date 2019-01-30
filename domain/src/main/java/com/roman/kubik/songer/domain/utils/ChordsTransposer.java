package com.roman.kubik.songer.domain.utils;

import com.roman.kubik.songer.domain.song.Song;

import java.util.Set;

import javax.inject.Inject;

public class ChordsTransposer {

    private final MarkedChordsRecognizer markedChordsRecognizer;
    private enum Transposition {
        UP, DOWN
    }

    @Inject
    public ChordsTransposer(MarkedChordsRecognizer markedChordsRecognizer) {
        this.markedChordsRecognizer = markedChordsRecognizer;
    }

    public Song transposeUp(Song song) {
        return transpose(song, Transposition.UP);
    }

    public Song transposeDown(Song song) {
        return transpose(song, Transposition.DOWN);
    }

    private Song transpose(Song song, Transposition transposition) {
        Set<String> chords = markedChordsRecognizer.findSelections(song.getLyrics());
        for (String chord : chords) {
            String newChord = move(chord, transposition);
            chord = markChord(chord);
            String lyrics = song.getLyrics().replaceAll(chord, newChord);
            song.setLyrics(lyrics);
        }
        String newLyrics = removeMarks(song.getLyrics());
        song.setLyrics(newLyrics);
        return song;
    }

    private String move(String chord, Transposition transposition) {
        String modifier = getModifier(chord);
        chord = normalizeChord(chord, modifier);
        int power = getPower(chord);
        power = calculateNewPower(power, transposition);
        chord = getChord(power);
        chord = applyModifier(chord, modifier);
        return chord.concat("!>");
    }

    private int calculateNewPower(int oldPower, Transposition transposition) {
        return transposition == Transposition.UP ? oldPower + 5 : oldPower -5;
    }

    private String getModifier(String chord) {
        String modifier = "";
        if (chord != null) {
            if (chord.contains("m")) {
                modifier = modifier.concat("m");
            }
            if (chord.contains("7")) {
                modifier = modifier.concat("7");
            }
        }
        return modifier;
    }

    private String normalizeChord(String chord, String modifier) {
        String c = chord;
        if (chord != null) {
            c = chord.replaceAll("\\s*", "");
            c = c.replace(modifier, "");
        }
        return c;
    }

    private String applyModifier(String chord, String modifier) {
        chord = chord.length() > 1
                ? chord.substring(0, 1).concat(modifier).concat(chord.substring(1, 2))
                : chord.length() > 0 ? chord.concat(modifier) : chord;
        return chord;
    }

    private String markChord(String chord) {
        return chord.concat(">");
    }

    private String removeMarks(String lyrics) {
        return lyrics.replaceAll("!>", ">");
    }

    private int getPower(String chord) {
        switch (chord) {
            case "B#":
            case "H#":
            case "C":
                return 0;
            case "C#":
            case "Db":
                return 5;
            case "D":
                return 10;
            case "D#":
            case "Eb":
                return 15;
            case "E":
            case "Fb":
                return 20;
            case "E#":
            case "F":
                return 25;
            case "F#":
            case "Gb":
                return 30;
            case "G":
                return 35;
            case "G#":
            case "Ab":
                return 40;
            case "A":
                return 45;
            case "A#":
            case "Bb":
            case "Hb":
                return 50;
            case "B":
            case "H":
            case "Cb":
                return 55;
            default:
                return -1;
        }
    }

    private String getChord(int power) {
        switch ((60 + power) % 60) {
            case 0:
                return "C";
            case 5:
                return "C#";
            case 10:
                return "D";
            case 15:
                return "D#";
            case 20:
                return "E";
            case 25:
                return "F";
            case 30:
                return "F#";
            case 35:
                return "G";
            case 40:
                return "G#";
            case 45:
                return "A";
            case 50:
                return "A#";
            case 55:
                return "H";
            default:
                return "";

        }
    }

}
