package com.roman.kubik.spivanyklicejista.presentation.view

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import butterknife.ButterKnife
import butterknife.OnClick
import com.roman.kubik.spivanyklicejista.R
import com.roman.kubik.spivanyklicejista.domain.chord.Chord
import com.roman.kubik.spivanyklicejista.utils.AssetsDrawableLoader
import kotlinx.android.synthetic.main.dialog_chord.*

class ChordDialog : Dialog {

    private val assetsDrawableLoader: AssetsDrawableLoader
    private val chords: List<Chord>
    private var currentPosition: Int = 0

    constructor(context: Context?, chords: List<Chord>, assetsDrawableLoader: AssetsDrawableLoader) : super(context) {
        this.chords = chords
        this.assetsDrawableLoader = assetsDrawableLoader
        init()
    }

    constructor(context: Context?, themeResId: Int, chords: List<Chord>, assetsDrawableLoader: AssetsDrawableLoader) : super(context, themeResId) {
        this.chords = chords
        init()
        this.assetsDrawableLoader = assetsDrawableLoader
    }

    constructor(context: Context?, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?, chords: List<Chord>, assetsDrawableLoader: AssetsDrawableLoader) : super(context, cancelable, cancelListener) {
        this.chords = chords
        init()
        this.assetsDrawableLoader = assetsDrawableLoader
    }

    private fun init() {
        setContentView(R.layout.dialog_chord)
        ButterKnife.bind(this)
        if (chords.size > currentPosition) {
            chord.text = chords[currentPosition].name
            chordImage.setImageDrawable(assetsDrawableLoader.loadDrawable(chords[currentPosition].imagePath))
            setCurrentPage()
        }
    }

    private fun setCurrentPage() {
        indicator.text = String.format(context.getString(R.string.page_of_pages), currentPosition + 1, chords.size)
    }

    fun showActiveChord(id: Int) {
        val c = chords.findLast { c -> c.id == id }
        if (c != null) {
            chord.text = c.name
            chordImage.setImageDrawable(assetsDrawableLoader.loadDrawable(c.imagePath))
            currentPosition = chords.indexOf(c)
            setCurrentPage()
            show()
        }
    }

    fun showActiveChordPosition(position: Int) {
        if (chords.size > position && position > -1) {
            chord.text = chords[position].name
            chordImage.setImageDrawable(assetsDrawableLoader.loadDrawable(chords[position].imagePath))
            currentPosition = position
            setCurrentPage()
            show()
        }
    }

    fun showActiveChordName(name: String) {
        val c = chords.findLast { c -> c.name.toLowerCase() == name.toLowerCase() }
        if (c != null) {
            chord.text = c.name
            chordImage.setImageDrawable(assetsDrawableLoader.loadDrawable(c.imagePath))
            currentPosition = chords.indexOf(c)
            setCurrentPage()
            show()
        }
    }

    @OnClick(R.id.closeButton)
    fun close() {
        dismiss()
    }

    @OnClick(R.id.nextButton)
    fun forward() {
        showActiveChordPosition(currentPosition + 1)
    }

    @OnClick(R.id.backButton)
    fun backward() {
        showActiveChordPosition(currentPosition - 1)
    }
}
