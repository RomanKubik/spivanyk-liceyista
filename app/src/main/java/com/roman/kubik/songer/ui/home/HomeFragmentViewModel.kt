package com.roman.kubik.songer.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.roman.kubik.songer.R
import com.roman.kubik.songer.data.core.StringProvider
import com.roman.kubik.songer.ui.base.BaseViewModel

class HomeFragmentViewModel @ViewModelInject constructor(private val stringProvider: StringProvider) : BaseViewModel() {

    private val _categories = MutableLiveData<List<HomeCategory>>()
    val categories: LiveData<List<HomeCategory>> = _categories

    init {
        fillCategories()
    }

    fun onCategorySelected(category: HomeCategory) {

    }

    private fun fillCategories() {
        val list = mutableListOf<HomeCategory>()
        list.add(HomeCategory(CATEGORY_LAST_PLAYED,
                stringProvider.getString(R.string.home_category_last_played),
                stringProvider.getString(R.string.home_category_last_played_subtitle),
                R.drawable.ic_home))
        list.add(HomeCategory(CATEGORY_MY_SONGS,
                stringProvider.getString(R.string.home_category_my_songs),
                stringProvider.getString(R.string.home_category_my_songs_subtitle),
                R.drawable.ic_home))
        list.add(HomeCategory(CATEGORY_FAVOURITE,
                stringProvider.getString(R.string.home_category_favourite),
                stringProvider.getString(R.string.home_category_favourite_subtitle),
                R.drawable.ic_home))
        list.add(HomeCategory(CATEGORY_PATRIOTIC,
                stringProvider.getString(R.string.home_category_patriotic),
                stringProvider.getString(R.string.home_category_patriotic_subtitle),
                R.drawable.ic_home))
        list.add(HomeCategory(CATEGORY_BONFIRE,
                stringProvider.getString(R.string.home_category_bonfire),
                stringProvider.getString(R.string.home_category_bonfire_subtitle),
                R.drawable.ic_home))
        list.add(HomeCategory(CATEGORY_ABROAD,
                stringProvider.getString(R.string.home_category_abroad),
                stringProvider.getString(R.string.home_category_abroad_subtitle),
                R.drawable.ic_home))
        _categories.value = list
    }

    companion object {
        const val CATEGORY_LAST_PLAYED = 0
        const val CATEGORY_MY_SONGS = 1
        const val CATEGORY_FAVOURITE = 2
        const val CATEGORY_PATRIOTIC = 3
        const val CATEGORY_BONFIRE = 4
        const val CATEGORY_ABROAD = 5
    }
}