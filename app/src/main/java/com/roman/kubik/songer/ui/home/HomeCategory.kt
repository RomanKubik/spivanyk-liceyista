package com.roman.kubik.songer.ui.home

import androidx.annotation.DrawableRes
import com.roman.kubik.songer.domain.models.Category

data class HomeCategory(val categoryId: Int,
                        val title: CharSequence,
                        val subtitle: CharSequence,
                        @DrawableRes val icon: Int)