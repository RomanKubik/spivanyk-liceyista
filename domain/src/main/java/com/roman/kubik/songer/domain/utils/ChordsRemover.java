package com.roman.kubik.songer.domain.utils;

import com.roman.kubik.songer.domain.formatting.LyricsFormatter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

public class ChordsRemover implements LyricsFormatter {

    private static final Pattern bracketsPattern = Pattern.compile("(<\\S{1,4}>)");
    private static final Pattern emptyLinePattern = Pattern.compile("(\\n\\W+\\n)");

    @Inject
    public ChordsRemover() {
    }

    @Override
    public CharSequence format(String text) {
        Matcher matcher = bracketsPattern.matcher(text);
        String str = text;
        while (matcher.find()) {
            String chord = matcher.group(1);
            str = str.replaceAll(chord, " ");
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
