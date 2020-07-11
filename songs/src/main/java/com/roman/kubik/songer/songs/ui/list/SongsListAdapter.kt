package com.roman.kubik.songer.songs.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songs.R
import kotlinx.android.synthetic.main.item_song_list.view.*

class SongsListAdapter : RecyclerView.Adapter<SongsListAdapter.SongHolder>() {
    private var items = mutableListOf<Song>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SongHolder(inflater.inflate(R.layout.item_song_list, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.bind(items[position])
    }

    fun publishItems(items: List<Song>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class SongHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(song: Song) {
            itemView.title.text = song.title
            itemView.lyrics.text = song.lyrics
        }

    }
}