package com.roman.kubik.spivanyklicejista.utils

import android.content.Context
import com.roman.kubik.spivanyklicejista.Constants
import com.roman.kubik.spivanyklicejista.R
import javax.inject.Inject

class CategoryTitleMapper @Inject constructor(private val context: Context){

    fun getCategoryTitle(categoryId: Int) : String {
        when (categoryId) {
            Constants.Category.FAVOURITE_ID -> return context.getString(R.string.ttl_favourite)
            Constants.Category.ALL_ID -> return context.getString(R.string.ttl_all_songs)
            Constants.Category.ABROAD_ID -> return context.getString(R.string.ttl_abroad_songs)
            Constants.Category.BONFIRE_ID -> return context.getString(R.string.ttl_bonfire_songs)
            Constants.Category.PATRIOTIC_ID -> return context.getString(R.string.ttl_patriotic)
            Constants.Category.LAST_ID -> return context.getString(R.string.ttl_last_played)
        }
        return ""
    }
}
