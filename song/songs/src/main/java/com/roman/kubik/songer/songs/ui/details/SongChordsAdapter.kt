package com.roman.kubik.songer.songs.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roman.kubik.settings.domain.preference.Instrument
import com.roman.kubik.songer.chords.model.Chord
import com.roman.kubik.songer.core.ui.utils.AssetImageLoader
import com.roman.kubik.songer.songs.ui.view.ChordClickListener
import com.roman.kubik.songs.R
import kotlinx.android.synthetic.main.item_chord.view.*

class SongChordsAdapter(private val chordClickListener: ChordClickListener) : RecyclerView.Adapter<SongChordsAdapter.ChordViewHolder>() {

    private val items = ArrayList<Chord>()
    var selectedInstrument: Instrument = Instrument.GUITAR
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChordViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ChordViewHolder(inflater.inflate(R.layout.item_chord, parent, false), chordClickListener)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ChordViewHolder, position: Int) {
        holder.bind(items[position], selectedInstrument)
    }

    fun publishItems(items: List<Chord>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class ChordViewHolder constructor(itemView: View, private val chordClickListener: ChordClickListener) : RecyclerView.ViewHolder(itemView) {

        fun bind(chord: Chord, instrument: Instrument) {
            itemView.chordName.text = chord.name
            itemView.chordImage.setImageDrawable(AssetImageLoader.loadAsset(itemView.context, chord.imagePath?.format(instrument.name.toLowerCase())))
            itemView.setOnClickListener {
                chordClickListener.onChordClicked(chord.name)
            }
        }

    }
}