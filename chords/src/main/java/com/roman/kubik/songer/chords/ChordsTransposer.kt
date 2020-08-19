package com.roman.kubik.songer.chords

import java.util.regex.Matcher
import java.util.regex.Pattern


object ChordsTransposer {

    private val bracketsPattern: Pattern = Pattern.compile("(<\\S+>)")

    private enum class Transposition {
        UP, DOWN
    }

    fun transposeUp(song: String): String {
        return transpose(song, Transposition.UP)
    }

    fun transposeDown(song: String): String {
        return transpose(song, Transposition.DOWN)
    }

    private fun transpose(song: String, transposition: Transposition): String {
        var lyrics = song
        val chords: Set<String> = findSelections(lyrics)
        for (chord in chords) {
            val newChord = move(chord, transposition)
            val markedChord = markChord(chord)
            lyrics = lyrics.replace(markedChord.toRegex(), newChord)
        }
        return removeMarks(lyrics)
    }

    private fun move(chord: String, transposition: Transposition): String {
        var chord: String? = chord
        val modifier = getModifier(chord)
        chord = normalizeChord(chord, modifier)
        var power = getPower(chord)
        power = calculateNewPower(power, transposition)
        chord = getChord(power)
        chord = applyModifier(chord, modifier)
        return "$chord!>"
    }

    private fun calculateNewPower(oldPower: Int, transposition: Transposition): Int {
        return if (transposition === Transposition.UP) oldPower + 5 else oldPower - 5
    }

    private fun getModifier(chord: String?): String {
        var modifier = ""
        if (chord != null) {
            if (chord.contains("m")) {
                modifier = modifier + "m"
            }
            if (chord.contains("7")) {
                modifier = modifier + "7"
            }
        }
        return modifier
    }

    private fun normalizeChord(chord: String?, modifier: String): String? {
        var c = chord
        if (chord != null) {
            c = chord.replace("\\s*".toRegex(), "")
            c = c.replace(modifier, "")
        }
        return c
    }

    private fun applyModifier(chord: String?, modifier: String): String? {
        var chord = chord
        chord = if (chord!!.length > 1) chord.substring(0, 1) + modifier + chord.substring(1, 2) else if (chord.length > 0) chord + modifier else chord
        return chord
    }

    private fun markChord(chord: String): String {
        return "$chord>"
    }

    private fun removeMarks(lyrics: String): String {
        return lyrics.replace("!>".toRegex(), ">")
    }

    private fun getPower(chord: String?): Int {
        return when (chord) {
            "B#", "H#", "C" -> 0
            "C#", "Db" -> 5
            "D" -> 10
            "D#", "Eb" -> 15
            "E", "Fb" -> 20
            "E#", "F" -> 25
            "F#", "Gb" -> 30
            "G" -> 35
            "G#", "Ab" -> 40
            "A" -> 45
            "A#", "Bb", "Hb" -> 50
            "B", "H", "Cb" -> 55
            else -> -1
        }
    }

    private fun getChord(power: Int): String? {
        return when ((60 + power) % 60) {
            0 -> "C"
            5 -> "C#"
            10 -> "D"
            15 -> "D#"
            20 -> "E"
            25 -> "F"
            30 -> "F#"
            35 -> "G"
            40 -> "G#"
            45 -> "A"
            50 -> "A#"
            55 -> "H"
            else -> ""
        }
    }

    private fun findSelections(text: String?): Set<String> {
        val selectionsList: MutableSet<String> = HashSet()
        val matcher: Matcher = bracketsPattern.matcher(text)
        while (matcher.find()) {
            var chord: String = matcher.group(1)
            chord = clearFormatting(chord)
            selectionsList.add(chord)
        }
        return selectionsList
    }

    private fun clearFormatting(text: String): String {
        var txt = text.replace("<", "")
        txt = txt.replace(">", "")
        return txt
    }
}