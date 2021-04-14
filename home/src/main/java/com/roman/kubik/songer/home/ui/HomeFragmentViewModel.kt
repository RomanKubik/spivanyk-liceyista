package com.roman.kubik.songer.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.roman.kubik.settings.domain.repository.HintsConfigRepository
import com.roman.kubik.songer.analytics.core.AnalyticsService
import com.roman.kubik.songer.core.Command
import com.roman.kubik.songer.core.data.StringProvider
import com.roman.kubik.songer.core.navigation.SearchNavigator
import com.roman.kubik.songer.core.ui.base.search.BaseSearchViewModel
import com.roman.kubik.songer.home.R
import com.roman.kubik.songer.home.analytics.SelectedHomeCategoryEvent
import com.roman.kubik.songer.home.navigation.HomeNavigator
import com.roman.kubik.songer.songs.domain.song.SongCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
        private val hintsConfigRepository: HintsConfigRepository,
        private val stringProvider: StringProvider,
        private val analyticsService: AnalyticsService,
        private val homeNavigator: HomeNavigator,
        searchNavigator: SearchNavigator
) : BaseSearchViewModel(searchNavigator) {

    private val _categories = MutableLiveData<List<HomeCategory>>()
    val categories: LiveData<List<HomeCategory>> = _categories
    val showShakeHintCommand = Command<Unit>()

    init {
        fillCategories()
        fetchHintsConfig()
    }

    fun onCategorySelected(category: HomeCategory) {
        analyticsService.log(SelectedHomeCategoryEvent(category))
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

    private fun fetchHintsConfig() {
        viewModelScope.launch(Dispatchers.IO) {
            val hints = hintsConfigRepository.getHintsConfig()
            if (!hints.shakeHintShown) {
                showShakeHintCommand.postValue(Unit)
                hintsConfigRepository.updateHintsConfig(hints.copy(shakeHintShown = true))
            }
        }
    }
}