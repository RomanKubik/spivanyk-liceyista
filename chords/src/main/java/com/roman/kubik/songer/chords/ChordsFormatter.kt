package com.roman.kubik.songer.chords

import java.util.regex.Pattern

object ChordsFormatter {

    val bracketsPattern = Pattern.compile("(<\\S+>)")

    fun clearChordMarks(text: String): String {
        var txt = text.replace("<", "")
        txt = txt.replace(">", "")
        return txt
    }
}