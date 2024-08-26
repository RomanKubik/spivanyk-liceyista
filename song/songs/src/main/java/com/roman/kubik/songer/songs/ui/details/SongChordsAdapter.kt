package com.roman.kubik.songer.songs.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roman.kubik.settings.domain.preference.Instrument
import com.roman.kubik.songer.chords.model.Chord
import com.roman.kubik.songer.core.ui.utils.AssetImageLoader
import com.roman.kubik.songer.song.songs.databinding.ItemChordBinding
import com.roman.kubik.songer.songs.ui.view.ChordClickListener
import java.util.Locale

class SongChordsAdapter(private val chordClickListener: ChordClickListener) :
    RecyclerView.Adapter<SongChordsAdapter.ChordViewHolder>() {

    private val items = ArrayList<Chord>()
    var selectedInstrument: Instrument = Instrument.GUITAR
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChordViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ChordViewHolder(
            ItemChordBinding.inflate(inflater, parent, false),
            chordClickListener
        )
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

    class ChordViewHolder constructor(
        private val binding: ItemChordBinding,
        private val chordClickListener: ChordClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(chord: Chord, instrument: Instrument) {
            binding.chordName.text = chord.name
            binding.chordImage.setImageDrawable(
                AssetImageLoader.loadAsset(
                    itemView.context, chord.imagePath?.format(
                        instrument.name.lowercase(Locale.getDefault())
                    )
                )
            )
            binding.root.setOnClickListener {
                chordClickListener.onChordClicked(chord.name)
            }
        }

    }
}