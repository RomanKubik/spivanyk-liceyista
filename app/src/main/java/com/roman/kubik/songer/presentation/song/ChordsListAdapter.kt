package com.roman.kubik.songer.presentation.song

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.annimon.stream.function.Consumer
import com.roman.kubik.songer.R
import com.roman.kubik.songer.domain.chord.Chord
import com.roman.kubik.songer.utils.AssetsDrawableLoader
import javax.inject.Inject

class ChordsListAdapter @Inject constructor(private val assetsDrawableLoader: AssetsDrawableLoader)
    : androidx.recyclerview.widget.RecyclerView.Adapter<ChordsListAdapter.ChordHolder>() {

    private val chords = mutableListOf<Chord>()
    var chordClickListener: Consumer<Chord>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChordHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ChordHolder(inflater.inflate(R.layout.item_chord, parent, false),
                assetsDrawableLoader)
    }

    override fun getItemCount() = chords.size

    override fun onBindViewHolder(holder: ChordHolder, position: Int) {
        holder.setChord(chords[position])
        holder.setOnClickListener(Consumer { chordClickListener?.accept(chords[it]) })
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

    class ChordHolder(itemView: View, private val assetsDrawableLoader: AssetsDrawableLoader) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

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

        fun setOnClickListener(clickListener: Consumer<Int>) {
            itemView.setOnClickListener { clickListener.accept(this.adapterPosition) }

        }

    }
}