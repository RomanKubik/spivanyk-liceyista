package com.roman.kubik.songer.home.ui

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.roman.kubik.songer.songs.domain.song.SongCategory

data class HomeCategory(val songCategory: SongCategory,
                        val title: String,
                        val subtitle: String,
                        @DrawableRes val icon: Int,
                        @ColorRes val iconBackgroundColor: Int,)