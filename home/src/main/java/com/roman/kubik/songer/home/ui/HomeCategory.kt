package com.roman.kubik.songer.home.ui

import androidx.annotation.DrawableRes
import com.roman.kubik.songer.songs.domain.song.SongCategory

data class HomeCategory(val songCategory: SongCategory,
                        val title: CharSequence,
                        val subtitle: CharSequence,
                        @DrawableRes val icon: Int)