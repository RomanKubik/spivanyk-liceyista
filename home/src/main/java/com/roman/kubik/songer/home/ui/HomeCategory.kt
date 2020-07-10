package com.roman.kubik.songer.home.ui

import androidx.annotation.DrawableRes

data class HomeCategory(val categoryId: Int,
                        val title: CharSequence,
                        val subtitle: CharSequence,
                        @DrawableRes val icon: Int)