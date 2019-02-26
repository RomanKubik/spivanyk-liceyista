package com.roman.kubik.songer.presentation.list

import androidx.recyclerview.widget.DiffUtil
import com.roman.kubik.songer.domain.song.Song

class SongsDiffCallback(val oldList: List<Song>, val newList: List<Song>): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition].hashCode() == newList[newItemPosition].hashCode()
}