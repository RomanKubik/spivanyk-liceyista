package com.roman.kubik.songer.presentation.list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.annimon.stream.function.Consumer
import com.roman.kubik.songer.R
import com.roman.kubik.songer.domain.category.Category
import com.roman.kubik.songer.domain.formatting.LyricsFormattingInteractor
import com.roman.kubik.songer.domain.song.Song
import javax.inject.Inject

/**
 * Songs main list adapter
 * Created by kubik on 1/20/18.
 */

class SongsAdapter @Inject
constructor(val lyricsFormattingInteractor: LyricsFormattingInteractor)
    : RecyclerView.Adapter<SongsAdapter.BaseSongHolder>() {

    private var onClickListener: OnItemClickListener? = null

    private val songList = mutableListOf<Song>()

    private var showChords = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSongHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_WEB -> BaseSongHolder(inflater.inflate(R.layout.item_song_web, parent, false))
            else -> SongHolder(inflater.inflate(R.layout.item_song, parent, false))
        }
    }

    override fun onBindViewHolder(holder: BaseSongHolder, position: Int) {
        holder.setItem(songList[position])
        if (onClickListener != null) {
            holder.setOnItemClickListener(onClickListener!!)
        }
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (songList[position].categoryId) {
            Category.WEB_ID -> TYPE_WEB
            else -> TYPE_GENERAL
        }
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

    open inner class BaseSongHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        protected lateinit var song: Song
        protected var tvTitle: TextView = itemView.findViewById(R.id.title)

        open fun setItem(song: Song) {
            this.song = song
            tvTitle.text = song.title
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
    }

    inner class SongHolder(itemView: View) : BaseSongHolder(itemView) {

        private var tvLyrics: TextView = itemView.findViewById(R.id.lyrics)

        override fun setItem(song: Song) {
            super.setItem(song)
            tvLyrics.text = getLyrics(song.lyrics)
        }

        private fun getLyrics(lyrics: String): CharSequence {
            return if (showChords)
                lyricsFormattingInteractor.createChords(lyrics, null, ContextCompat.getColor(tvLyrics.context, R.color.colorTextPrimary), Color.TRANSPARENT)
            else
                lyricsFormattingInteractor.removeChords(lyrics)
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(song: Song)
        fun onItemLongClicked(song: Song)
    }

    companion object SongType {
        const val TYPE_GENERAL = 0
        const val TYPE_WEB = 1
    }
}
