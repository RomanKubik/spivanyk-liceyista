package com.roman.kubik.spivanyklicejista.domain.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

public class MarkedChordsRecognizer {
    private static final Pattern bracketsPattern = Pattern.compile("(<\\S+>)");

    @Inject
    public MarkedChordsRecognizer() {
    }

    public Set<String> findSelections(String text) {
        Set<String> selectionsList = new HashSet<>();
        Matcher matcher = bracketsPattern.matcher(text);
        while (matcher.find()) {
            String chord = matcher.group(1);
            chord = clearFormatting(chord);
            selectionsList.add(chord);
        }
        return selectionsList;
    }

    private String clearFormatting(String text) {
        String txt = text.replace("<", "");
        txt = txt.replace(">", "");
        return txt;
    }
}
