package com.roman.kubik.spivanyklicejista.domain.utils;

import com.roman.kubik.spivanyklicejista.domain.formatting.LyricsFormatter;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

/**
 * Helps to recognize chords in the song
 * Created by kubik on 1/21/18.
 */

public class ChordsRecognizer implements LyricsFormatter {

    public static final String A = "A";
    public static final String B = "B";
    public static final String C = "C";
    public static final String D = "D";
    public static final String E = "E";
    public static final String F = "F";
    public static final String G = "G";
    public static final String H = "H";

    public static final String MINOR = "m";

    public static final String SEPT = "7";

    public static final String SHARP = "#";
    public static final String FLAT = "b";

    public static final String EMPTY_STRING = "";

    private static final List<String> CHORDS_GROUP = Arrays.asList(A, B, C, D, E, F, G, H);
    private static final List<String> MINOR_GROUP = Arrays.asList(EMPTY_STRING, MINOR);
    private static final List<String> SEPT_GROUP = Arrays.asList(EMPTY_STRING, SEPT);
    private static final List<String> ACCIDENTAL_GROUP = Arrays.asList(EMPTY_STRING, SHARP, FLAT);

    @Inject
    public ChordsRecognizer() {
    }

    /**
     * Takes song and marks chord inside with specific symbols.
     * For example Am -> <Am>
     *
     * @param text text to be recognized
     * @return recognized text
     */
    @Override
    public CharSequence format(String text) {

        String result = text;

        for (String chord : CHORDS_GROUP) {
            for (String minor : MINOR_GROUP) {
                for (String sept : SEPT_GROUP) {
                    for (String accidental : ACCIDENTAL_GROUP) {
                        String fullChord = chord.concat(minor).concat(sept).concat(accidental);
                        result = findAndReplace(result, fullChord);
                    }
                }
            }
        }

        return result;
    }

    /**
     * Helps to find specific chord inside the text
     *
     * @param text  text to be recognized
     * @param chord chord to find and replace
     * @return text with marked chord
     */
    private String findAndReplace(String text, String chord) {

        Pattern pattern = Pattern.compile("^" + chord + " ");
        Matcher mat = pattern.matcher(text);
        String replacement = "<" + chord + "> ";
        String result = mat.replaceAll(replacement);

        pattern = Pattern.compile(" " + chord + " ");
        mat = pattern.matcher(result);
        replacement = " <" + chord + "> ";
        result = mat.replaceAll(replacement);

        pattern = Pattern.compile("\n" + chord + " ");
        mat = pattern.matcher(result);
        replacement = "\n<" + chord + "> ";
        result = mat.replaceAll(replacement);

        pattern = Pattern.compile("^" + chord + "\n");
        mat = pattern.matcher(result);
        replacement = "<" + chord + ">\n";
        result = mat.replaceAll(replacement);

        pattern = Pattern.compile(" " + chord + "\n");
        mat = pattern.matcher(result);
        replacement = " <" + chord + ">\n";
        result = mat.replaceAll(replacement);

        pattern = Pattern.compile("\n" + chord + "\n");
        mat = pattern.matcher(result);
        replacement = "\n<" + chord + ">\n";
        result = mat.replaceAll(replacement);

        pattern = Pattern.compile(" " + chord + "$");
        mat = pattern.matcher(result);
        replacement = " <" + chord + ">";
        result = mat.replaceAll(replacement);

        pattern = Pattern.compile("\n" + chord + "$");
        mat = pattern.matcher(result);
        replacement = "\n<" + chord + ">";
        result = mat.replaceAll(replacement);

        return result;
    }


}
