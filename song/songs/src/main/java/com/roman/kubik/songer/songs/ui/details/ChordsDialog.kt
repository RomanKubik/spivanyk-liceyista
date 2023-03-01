package com.roman.kubik.songer.songs.ui.details

import android.content.Context
import androidx.appcompat.app.AppCompatDialog
import com.roman.kubik.settings.domain.preference.Instrument
import com.roman.kubik.songer.chords.model.Chord
import com.roman.kubik.songer.core.ui.utils.AssetImageLoader
import com.roman.kubik.songs.R
import kotlinx.android.synthetic.main.dialog_chord.*
import java.util.*

class ChordsDialog(context: Context,
                   private val chords: List<Chord>,
                   private val selectedInstrument: Instrument) : AppCompatDialog(context) {
    private var currentPosition: Int = 0
        set(value) {
            field = validatePage(value)
            val c = chords[field]
            chord.text = c.name
            chordImage.setImageDrawable(AssetImageLoader.loadAsset(context, c.imagePath?.format(
                selectedInstrument.name.lowercase()
            )))
            indicator.text = String.format(context.getString(R.string.chords_a_of_b), currentPosition + 1, chords.size)
            show()
        }

    init {
        setContentView(R.layout.dialog_chord)
        closeButton.setOnClickListener {
            dismiss()
        }
        nextButton.setOnClickListener {
            currentPosition++
        }
        prevButton.setOnClickListener {
            currentPosition--
        }
    }

    private fun validatePage(page: Int): Int {
        return when {
            page < 0 -> {
                chords.size - 1
            }
            page >= chords.size -> {
                0
            }
            else -> {
                page
            }
        }
    }

    fun showChord(chordName: String) {
        val c = chords.findLast { c -> c.name == chordName }
        if (c != null) {
            currentPosition = chords.indexOf(c)
        }
    }
}