package com.roman.kubik.songer.presentation.list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import com.annimon.stream.function.Consumer
import com.roman.kubik.songer.R
import com.roman.kubik.songer.domain.formatting.LyricsFormattingInteractor
import com.roman.kubik.songer.domain.song.Song
import javax.inject.Inject

/**
 * Songs main list adapter
 * Created by kubik on 1/20/18.
 */

class SongsAdapter @Inject
constructor(val lyricsFormattingInteractor: LyricsFormattingInteractor)
    : androidx.recyclerview.widget.RecyclerView.Adapter<SongsAdapter.SongHolder>() {

    private var onClickListener: OnItemClickListener? = null

    private val songList = mutableListOf<Song>()

    private var showChords = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SongHolder(inflater.inflate(R.layout.item_song, parent, false))
    }

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.setItem(songList[position])
        if (onClickListener != null) {
            holder.setOnItemClickListener(onClickListener!!)
        }
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    fun setShowChords(showChords: Boolean) {
        this.showChords = showChords
        notifyDataSetChanged()
    }

    fun setOnClickListener(onClickListener: OnItemClickListener) {
        this.onClickListener = onClickListener
    }

    fun setSongList(songList: List<Song>?) {
        if (songList != null) {
            val diffResult = DiffUtil.calculateDiff(SongsDiffCallback(this.songList, songList))
            this.songList.clear()
            this.songList.addAll(songList)
            diffResult.dispatchUpdatesTo(this)
        }
    }

    inner class SongHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

        private lateinit var song: Song
        private var tvTitle: TextView = itemView.findViewById(R.id.title)
        private var tvLyrics: TextView = itemView.findViewById(R.id.lyrics)

        fun setItem(song: Song) {
            this.song = song
            tvTitle.text = song.title
            tvLyrics.text = getLyrics(song.lyrics)
        }

        fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
            tvTitle.rootView.setOnClickListener{
                onItemClickListener.onItemClicked(song)
            }
            tvTitle.rootView.setOnLongClickListener {
                onItemClickListener.onItemLongClicked(song)
                true
            }
        }

        private fun getLyrics(lyrics: String): CharSequence {
            return if (showChords)
                lyricsFormattingInteractor.createChords(lyrics, null, ContextCompat.getColor(tvTitle.context, R.color.colorTextPrimary), Color.TRANSPARENT)
            else
                lyricsFormattingInteractor.removeChords(lyrics)
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(song: Song)
        fun onItemLongClicked(song: Song)
    }
}
