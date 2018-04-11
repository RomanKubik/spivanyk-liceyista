package com.roman.kubik.spivanyklicejista.presentation.song

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.roman.kubik.spivanyklicejista.R
import com.roman.kubik.spivanyklicejista.domain.chord.Chord
import com.roman.kubik.spivanyklicejista.utils.AssetsDrawableLoader
import javax.inject.Inject

class ChordsListAdapter @Inject constructor(private val assetsDrawableLoader: AssetsDrawableLoader)
    : RecyclerView.Adapter<ChordsListAdapter.ChordHolder>() {

    private val chords = mutableListOf<Chord>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChordHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ChordHolder(inflater.inflate(R.layout.item_chord, parent, false), assetsDrawableLoader)
    }

    override fun getItemCount() = chords.size

    override fun onBindViewHolder(holder: ChordHolder?, position: Int) {
        holder?.setChord(chords[position])
    }

    fun setChords(chords: List<Chord>) {
        this.chords.clear()
        this.chords.addAll(chords)
        notifyDataSetChanged()
    }

    fun addChords(chords: List<Chord>) {
        this.chords.addAll(chords)
        notifyDataSetChanged()
    }

    class ChordHolder(itemView: View, private val assetsDrawableLoader: AssetsDrawableLoader) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.chordName)
        lateinit var chordName: TextView
        @BindView(R.id.chordImage)
        lateinit var chordImage: ImageView

        init {
            ButterKnife.bind(this, itemView)
        }

        fun setChord(chord: Chord) {
            chordName.text = chord.name
            chordImage.setImageDrawable(assetsDrawableLoader.loadDrawable(chord.imagePath))
        }

    }
}