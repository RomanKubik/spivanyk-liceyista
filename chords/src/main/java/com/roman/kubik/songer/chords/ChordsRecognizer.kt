package com.roman.kubik.songer.chords

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Helps to recognize chords in the song
 */
object ChordsRecognizer {

    private const val A = "A"
    private const val B = "B"
    private const val C = "C"
    private const val D = "D"
    private const val E = "E"
    private const val F = "F"
    private const val G = "G"
    private const val H = "H"

    private const val MINOR = "m"
    private const val SEPT = "7"

    private const val SHARP = "#"
    private const val FLAT = "b"

    private const val EMPTY_STRING = ""

    private val CHORDS_GROUP = listOf(A, B, C, D, E, F, G, H)
    private val MINOR_GROUP = listOf(EMPTY_STRING, MINOR)
    private val SEPT_GROUP = listOf(EMPTY_STRING, SEPT)
    private val ACCIDENTAL_GROUP = listOf(EMPTY_STRING, SHARP, FLAT)


    /**
     * Takes song and marks chord inside with specific symbols.
     * For example Am -> <Am>
     *
     * @param text text to be recognized
     * @return recognized text
    </Am> */
    fun recognizeChords(text: String): String {
        var result = text
        for (chord in CHORDS_GROUP) {
            for (minor in MINOR_GROUP) {
                for (sept in SEPT_GROUP) {
                    for (accidental in ACCIDENTAL_GROUP) {
                        val fullChord = chord + minor + sept + accidental
                        result = findAndReplace(result, fullChord)
                    }
                }
            }
        }
        return result
    }

    /**
     * Helps to find specific chord inside the text
     *
     * @param text  text to be recognized
     * @param chord chord to find and replace
     * @return text with marked chord
     */
    private fun findAndReplace(text: String, chord: String): String {

        var pattern: Pattern = Pattern.compile("^$chord ")
        var mat: Matcher = pattern.matcher(text)
        var replacement = "<$chord> "
        var result: String = mat.replaceAll(replacement)

        pattern = Pattern.compile(" $chord ")
        mat = pattern.matcher(result)
        replacement = " <$chord> "
        result = mat.replaceAll(replacement)

        pattern = Pattern.compile("\n$chord ")
        mat = pattern.matcher(result)
        replacement = "\n<$chord> "
        result = mat.replaceAll(replacement)

        pattern = Pattern.compile("^$chord\n")
        mat = pattern.matcher(result)
        replacement = "<$chord>\n"
        result = mat.replaceAll(replacement)

        pattern = Pattern.compile(" $chord\n")
        mat = pattern.matcher(result)
        replacement = " <$chord>\n"
        result = mat.replaceAll(replacement)

        pattern = Pattern.compile("\n$chord\n")
        mat = pattern.matcher(result)
        replacement = "\n<$chord>\n"
        result = mat.replaceAll(replacement)

        pattern = Pattern.compile(" $chord$")
        mat = pattern.matcher(result)
        replacement = " <$chord>"
        result = mat.replaceAll(replacement)

        pattern = Pattern.compile("\n$chord$")
        mat = pattern.matcher(result)
        replacement = "\n<$chord>"
        result = mat.replaceAll(replacement)

        return result
    }
}