package com.roman.kubik.songer.songs.ui.details

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import com.roman.kubik.settings.domain.preference.Instrument
import com.roman.kubik.songer.chords.model.Chord
import com.roman.kubik.songer.core.ui.utils.AssetImageLoader
import com.roman.kubik.songer.song.songs.R
import com.roman.kubik.songer.song.songs.databinding.DialogChordBinding

class ChordsDialog(
    context: Context,
    private val chords: List<Chord>,
    private val selectedInstrument: Instrument
) : Dialog(context) {

    private lateinit var binding: DialogChordBinding
    private var currentPosition: Int = 0
        set(value) {
            field = validatePage(value)
            val c = chords[field]
            binding.chord.text = c.name
            binding.chordImage.setImageDrawable(
                AssetImageLoader.loadAsset(
                    context,
                    c.imagePath?.format(selectedInstrument.name.toLowerCase())
                )
            )
            binding.indicator.text = String.format(
                context.getString(R.string.chords_a_of_b),
                currentPosition + 1,
                chords.size
            )
            show()
        }

    init {
        binding = DialogChordBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        binding.closeButton.setOnClickListener {
            dismiss()
        }
        binding.nextButton.setOnClickListener {
            currentPosition++
        }
        binding. prevButton.setOnClickListener {
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