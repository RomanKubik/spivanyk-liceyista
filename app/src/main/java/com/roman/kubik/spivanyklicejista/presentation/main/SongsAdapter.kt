package com.roman.kubik.spivanyklicejista.presentation.main

import android.support.v7.widget.RecyclerView
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.annimon.stream.function.Consumer
import com.roman.kubik.spivanyklicejista.R
import com.roman.kubik.spivanyklicejista.domain.song.Song
import com.roman.kubik.spivanyklicejista.utils.SpannableStringChordsCreator
import javax.inject.Inject

/**
 * Songs main list adapter
 * Created by kubik on 1/20/18.
 */

class SongsAdapter @Inject
constructor(val chordsCreator: SpannableStringChordsCreator) : RecyclerView.Adapter<SongsAdapter.SongHolder>() {

    private var onClickListener: Consumer<Song>? = null

    private val songList = mutableListOf<Song>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SongHolder(inflater.inflate(R.layout.item_song, parent, false))
    }

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.setItem(songList[position])
        holder.setOnItemClickListener(View.OnClickListener {
            onClickListener?.accept(songList[holder.adapterPosition])
        })
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    fun setOnClickListener(onClickListener: Consumer<Song>?) {
        this.onClickListener = onClickListener
    }

    fun setSongList(songList: List<Song>?) {
        this.songList.clear()
        if (songList != null)
            this.songList.addAll(songList)
        notifyDataSetChanged()
    }

    fun addSongList(songList: List<Song>?) {
        if (songList != null) {
            val positionStart = this.songList.size
            this.songList.addAll(songList)
            notifyItemRangeInserted(positionStart, songList.size)
        }
    }

    inner class SongHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private var tvLyrics: TextView = itemView.findViewById(R.id.tvLyrics)

        fun setItem(song: Song) {
            tvTitle.text = song.title
            tvLyrics.text = chordsCreator.selectChords(song.lyrics, null)
        }

        fun setOnItemClickListener(onClickListener: View.OnClickListener) {
            tvTitle.rootView.setOnClickListener(onClickListener)
        }
    }
}
