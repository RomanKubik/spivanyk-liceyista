package com.roman.kubik.songer.utils

import android.content.Context
import com.roman.kubik.songer.R
import com.roman.kubik.songer.domain.category.Category
import javax.inject.Inject

class CategoryTitleMapper @Inject constructor(private val context: Context){

    fun getCategoryTitle(categoryId: Int) : String {
        when (categoryId) {
            Category.FAVOURITE_ID -> return context.getString(R.string.ttl_favourite)
            Category.ALL_ID -> return context.getString(R.string.ttl_all_songs)
            Category.ABROAD_ID -> return context.getString(R.string.ttl_abroad_songs)
            Category.BONFIRE_ID -> return context.getString(R.string.ttl_bonfire_songs)
            Category.PATRIOTIC_ID -> return context.getString(R.string.ttl_patriotic)
            Category.LAST_ID -> return context.getString(R.string.ttl_last_played)
            Category.USERS_ID -> return context.getString(R.string.ttl_my_songs)
            Category.WEB_ID -> return context.getString(R.string.ttl_web)
        }
        return ""
    }
}
