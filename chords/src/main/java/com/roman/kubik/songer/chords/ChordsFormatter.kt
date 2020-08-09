package com.roman.kubik.songer.chords

import java.util.regex.Pattern

object ChordsFormatter {

    private val emptyLinePattern = Pattern.compile("(\\n\\W+\\n)")
    val bracketsPattern = Pattern.compile("(<\\S+>)")

    fun removeChords(text: String): String {
        val matcher = bracketsPattern.matcher(text)
        var str = text
        while (matcher.find()) {
            val chord = matcher.group(1)
            str = str.replace(chord.toRegex(), " ")
        }
        return removeEmptyLines(str)
    }

    private fun removeEmptyLines(text: String): String {
        var str = text
        val matcher = emptyLinePattern.matcher(str)
        while (matcher.find()) {
            val emptyLine = matcher.group(1)
            str = str.replace(emptyLine.toRegex(), "\n")
        }
        return str
    }

    fun clearChordMarks(text: String): String {
        var txt = text.replace("<", "")
        txt = txt.replace(">", "")
        return txt
    }
}