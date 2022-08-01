package com.roman.kubik.songer.chords

import com.roman.kubik.songer.chords.model.Chord

object ChordsImageMapper {

    private const val FLAT = "b"
    private const val SHARP = "#"
    private val chordImageMap = hashMapOf(
            "A" to "chords/%s/A.png",
            "Am" to "chords/%s/Am.png",
            "A7" to "chords/%s/A7.png",
            "Am7" to "chords/%s/Am7.png",
            "Cb" to "chords/%s/B.png",
            "H#" to "chords/%s/B.png",
            "B" to "chords/%s/B.png",
            "Cmb" to "chords/%s/Bm.png",
            "Hm#" to "chords/%s/Bm.png",
            "Bm" to "chords/%s/Bm.png",
            "C7b" to "chords/%s/B7.png",
            "H7#" to "chords/%s/B7.png",
            "B7" to "chords/%s/B7.png",
            "Cm7b" to "chords/%s/Bm7.png",
            "Hm7#" to "chords/%s/Bm7.png",
            "Bm7" to "chords/%s/Bm7.png",
            "B#" to "chords/%s/C.png",
            "C" to "chords/%s/C.png",
            "С" to "chords/%s/C.png",
            "Bm#" to "chords/%s/Cm.png",
            "Cm" to "chords/%s/Cm.png",
            "B7#" to "chords/%s/C7.png",
            "C7" to "chords/%s/C7.png",
            "Bm7#" to "chords/%s/Cm7.png",
            "Cm7" to "chords/%s/Cm7.png",
            "D" to "chords/%s/D.png",
            "Dm" to "chords/%s/Dm.png",
            "D7" to "chords/%s/D7.png",
            "Dm7" to "chords/%s/Dm7.png",
            "Fb" to "chords/%s/E.png",
            "E" to "chords/%s/E.png",
            "Fmb" to "chords/%s/Em.png",
            "Em" to "chords/%s/Em.png",
            "F7b" to "chords/%s/E7.png",
            "E7" to "chords/%s/E7.png",
            "Fm7b" to "chords/%s/Em7.png",
            "Em7" to "chords/%s/Em7.png",
            "E#" to "chords/%s/F.png",
            "F" to "chords/%s/F.png",
            "Em#" to "chords/%s/Fm.png",
            "Fm" to "chords/%s/Fm.png",
            "E7#" to "chords/%s/F7.png",
            "F7" to "chords/%s/F7.png",
            "Em7#" to "chords/%s/Fm7.png",
            "Fm7" to "chords/%s/Fm7.png",
            "G" to "chords/%s/G.png",
            "Gm" to "chords/%s/Gm.png",
            "G7" to "chords/%s/G7.png",
            "Gm7" to "chords/%s/Gm7.png",
            "G#" to "chords/%s/Ab.png",
            "Ab" to "chords/%s/Ab.png",
            "Gm#" to "chords/%s/Abm.png",
            "Amb" to "chords/%s/Abm.png",
            "G7#" to "chords/%s/Ab7.png",
            "A7b" to "chords/%s/Ab7.png",
            "Gm7#" to "chords/%s/Abm7.png",
            "Am7b" to "chords/%s/Abm7.png",
            "A#" to "chords/%s/Bb.png",
            "H" to "chords/%s/Bb.png",
            "Bb" to "chords/%s/Bb.png",
            "A7#" to "chords/%s/Bb7.png",
            "H7" to "chords/%s/Bb7.png",
            "B7b" to "chords/%s/Bb7.png",
            "Am#" to "chords/%s/Bbm.png",
            "Hm" to "chords/%s/Bbm.png",
            "Bmb" to "chords/%s/Bbm.png",
            "Am7#" to "chords/%s/Bbm7.png",
            "Hm7" to "chords/%s/Bbm7.png",
            "Bm7b" to "chords/%s/Bbm7.png",
            "C#" to "chords/%s/Db.png",
            "Db" to "chords/%s/Db.png",
            "C7#" to "chords/%s/Db7.png",
            "Db7" to "chords/%s/Db7.png",
            "Cm#" to "chords/%s/Dbm.png",
            "Dmb" to "chords/%s/Dbm.png",
            "Cm7#" to "chords/%s/Dbm7.png",
            "Dm7b" to "chords/%s/Dbm7.png",
            "D#" to "chords/%s/Eb.png",
            "Eb" to "chords/%s/Eb.png",
            "D7#" to "chords/%s/Eb7.png",
            "E7b" to "chords/%s/Eb7.png",
            "Dm#" to "chords/%s/Ebm.png",
            "Emb" to "chords/%s/Ebm.png",
            "Dm7#" to "chords/%s/Ebm7.png",
            "Em7b" to "chords/%s/Ebm7.png",
            "Gb" to "chords/%s/F#.png",
            "F#" to "chords/%s/F#.png",
            "G7b" to "chords/%s/F#7.png",
            "F7#" to "chords/%s/F#7.png",
            "Gmb" to "chords/%s/F#m.png",
            "Fm#" to "chords/%s/F#m.png",
            "Gm7b" to "chords/%s/F#m7.png",
            "Fm7#" to "chords/%s/F#m7.png"
    )

    fun getChords(text: String): Set<Chord> {
        return getStringChords(text).map {
            val chord = unifyChord(it)
            val path = chordImageMap[chord]
            Chord(it, path)
        }.toSet()
    }

    private fun getStringChords(text: String): Set<String> {
        val selectionsList = LinkedHashSet<String>()
        val matcher = ChordsFormatter.bracketsPattern.matcher(text)
        while (matcher.find()) {
            var chord = matcher.group(1) ?: ""
            chord = ChordsFormatter.clearChordMarks(chord)
            selectionsList.add(chord)
        }
        return selectionsList
    }

    private fun unifyChord(chord: String): String {
        var unifiedChord = chord
        unifiedChord = unifiedChord.replace("\\s*".toRegex(), "")
        if (unifiedChord.contains(FLAT)) {
            unifiedChord = unifiedChord.replace(FLAT, "")
            unifiedChord += FLAT
        }
        if (unifiedChord.contains(SHARP)) {
            unifiedChord = unifiedChord.replace(SHARP, "")
            unifiedChord += SHARP
        }
        return unifiedChord
    }
}