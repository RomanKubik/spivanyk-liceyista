package com.roman.kubik.songer.songs.ui.details

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import com.roman.kubik.songer.chords.model.Chord
import com.roman.kubik.songer.core.ui.utils.AssetImageLoader
import com.roman.kubik.songs.R
import kotlinx.android.synthetic.main.dialog_chord.*

class ChordsDialog : Dialog {

    private lateinit var chords: List<Chord>
    private var currentPosition: Int = 0
        set(value) {
            field = validatePage(value)
            val c = chords[field]
            chord.text = c.name
            chordImage.setImageDrawable(AssetImageLoader.loadAsset(context, c.imagePath))
            indicator.text = String.format("%d of %d", currentPosition + 1, chords.size)
            show()
        }

    constructor(context: Context, chords: List<Chord>) : super(context) {
        init(chords)
    }

    constructor(context: Context, themeResId: Int, chords: List<Chord>) : super(context, themeResId) {
        init(chords)
    }

    constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(context, cancelable, cancelListener) {
        init(chords)
    }

    private fun init(chords: List<Chord>) {
        this.chords = chords
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