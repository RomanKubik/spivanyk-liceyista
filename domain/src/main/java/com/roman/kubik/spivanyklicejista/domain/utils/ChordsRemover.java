package com.roman.kubik.spivanyklicejista.domain.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

public class ChordsRemover {

    private static final Pattern bracketsPattern = Pattern.compile("(<\\S+>)");
    private static final Pattern emptyLinePattern = Pattern.compile("(\\n\\s+\\n)");

    @Inject
    public ChordsRemover() {
    }

    public String removeChords(String text) {
        Matcher matcher = bracketsPattern.matcher(text);
        String str = text;
        while (matcher.find()) {
            String chord = matcher.group(1);
            str = str.replaceAll(chord, "");
        }
        return removeEmptyLines(str);
    }

    private String removeEmptyLines(String text) {
        String str = text;
        Matcher matcher = emptyLinePattern.matcher(str);
        while (matcher.find()) {
            String emptyLine = matcher.group(1);
            str = str.replaceAll(emptyLine, "\n");
        }
        return str;
    }

}