package com.roman.kubik.songer.songs.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songs.R
import kotlinx.android.synthetic.main.item_song_list.view.*

class SongsListAdapter constructor(private val clickListener: (Song) -> Unit) : RecyclerView.Adapter<SongsListAdapter.SongHolder>() {
    private var items = mutableListOf<Song>()
    var showChords: Boolean = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SongHolder(inflater.inflate(R.layout.item_song_list, parent, false), clickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.bind(items[position], showChords)
    }

    fun publishItems(items: List<Song>) {
        val diffResult = DiffUtil.calculateDiff(SongsDiffCallback(this.items, items))
        this.items.clear()
        this.items.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    class SongHolder(itemView: View, private val clickListener: (Song) -> Unit) : RecyclerView.ViewHolder(itemView) {

        fun bind(song: Song, showChords: Boolean) {
            itemView.title.text = song.title
            itemView.lyrics.showChords = showChords
            itemView.lyrics.text = song.lyrics
            itemView.lyrics.isVisible = song.lyrics.isNotEmpty()
            itemView.source.text = song.source
            itemView.source.isVisible = song.source.isNotEmpty()
            itemView.setOnClickListener { clickListener.invoke(song) }
        }

    }
}