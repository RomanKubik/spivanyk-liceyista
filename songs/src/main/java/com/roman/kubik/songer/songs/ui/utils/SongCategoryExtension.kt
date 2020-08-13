package com.roman.kubik.songer.songs.ui.utils

import androidx.annotation.StringRes
import com.roman.kubik.songer.songs.domain.song.SongCategory
import com.roman.kubik.songs.R

@StringRes
fun SongCategory.toUiCategory(): Int {
    return when (this) {
        SongCategory.MY_SONGS -> R.string.song_category_my_song
        SongCategory.PATRIOTIC -> R.string.song_category_patriotic
        SongCategory.BONFIRE -> R.string.song_category_bonfire
        SongCategory.ABROAD -> R.string.song_category_abroad
        else -> R.string.empty_string
    }
}