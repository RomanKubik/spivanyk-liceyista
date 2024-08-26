package com.roman.kubik.songer.songs.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.roman.kubik.songer.song.songs.databinding.FragmentSongListBinding
import com.roman.kubik.songer.song.songs.databinding.ItemSongListBinding
import com.roman.kubik.songer.songs.domain.song.Song

class SongsListAdapter constructor(private val clickListener: (Song) -> Unit) :
    RecyclerView.Adapter<SongsListAdapter.SongHolder>() {
    private var items = mutableListOf<Song>()
    var showChords: Boolean = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SongHolder(ItemSongListBinding.inflate(inflater, parent, false), clickListener)
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

    class SongHolder(
        private val binding: ItemSongListBinding,
        private val clickListener: (Song) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(song: Song, showChords: Boolean) {
            binding.title.text = song.title
            binding.lyrics.showChords = showChords
            binding.lyrics.text = song.lyrics
            binding.lyrics.isVisible = song.lyrics.isNotEmpty()
            binding.source.text = song.source
            binding.source.isVisible = song.source.isNotEmpty()
            itemView.setOnClickListener { clickListener.invoke(song) }
        }

    }
}