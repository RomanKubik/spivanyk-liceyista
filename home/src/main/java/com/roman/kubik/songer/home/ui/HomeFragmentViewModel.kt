package com.roman.kubik.songer.home.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.roman.kubik.songer.core.data.StringProvider
import com.roman.kubik.songer.core.navigation.SearchNavigator
import com.roman.kubik.songer.core.ui.base.search.BaseSearchViewModel
import com.roman.kubik.songer.home.R
import com.roman.kubik.songer.home.navigation.HomeNavigator
import com.roman.kubik.songer.songs.domain.song.SongCategory

class HomeFragmentViewModel @ViewModelInject constructor(
        private val stringProvider: StringProvider,
        private val homeNavigator: HomeNavigator,
        searchNavigator: SearchNavigator
) : BaseSearchViewModel(searchNavigator) {

    private val _categories = MutableLiveData<List<HomeCategory>>()
    val categories: LiveData<List<HomeCategory>> = _categories

    init {
        fillCategories()
    }

    fun onCategorySelected(category: HomeCategory) {
        homeNavigator.navigateToCategory(category.songCategory)
    }

    private fun fillCategories() {
        val list = mutableListOf<HomeCategory>()
        list.add(HomeCategory(SongCategory.LAST_PLAYED,
                stringProvider.getString(R.string.home_category_last_played),
                stringProvider.getString(R.string.home_category_last_played_subtitle),
                R.drawable.ic_category_last_played))
        list.add(HomeCategory(SongCategory.MY_SONGS,
                stringProvider.getString(R.string.home_category_my_songs),
                stringProvider.getString(R.string.home_category_my_songs_subtitle),
                R.drawable.ic_category_my_songs))
        list.add(HomeCategory(SongCategory.FAVOURITE,
                stringProvider.getString(R.string.home_category_favourite),
                stringProvider.getString(R.string.home_category_favourite_subtitle),
                R.drawable.ic_category_favourite))
        list.add(HomeCategory(SongCategory.PATRIOTIC,
                stringProvider.getString(R.string.home_category_patriotic),
                stringProvider.getString(R.string.home_category_patriotic_subtitle),
                R.drawable.ic_category_patriotic))
        list.add(HomeCategory(SongCategory.BONFIRE,
                stringProvider.getString(R.string.home_category_bonfire),
                stringProvider.getString(R.string.home_category_bonfire_subtitle),
                R.drawable.ic_category_bonfire))
        list.add(HomeCategory(SongCategory.ABROAD,
                stringProvider.getString(R.string.home_category_abroad),
                stringProvider.getString(R.string.home_category_abroad_subtitle),
                R.drawable.ic_category_abroad))
        _categories.value = list
    }
}