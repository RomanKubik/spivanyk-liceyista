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
import com.roman.kubik.songer.songs.domain.repository.SongRepository
import com.roman.kubik.songer.songs.domain.song.SongCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val hintsConfigRepository: HintsConfigRepository,
    private val stringProvider: StringProvider,
    private val analyticsService: AnalyticsService,
    private val homeNavigator: HomeNavigator,
    private val songRepository: SongRepository,
    searchNavigator: SearchNavigator
) : BaseSearchViewModel(searchNavigator) {

    private val _categories = MutableLiveData<List<HomeCategory>>()
    val categories: LiveData<List<HomeCategory>> = _categories
    val showHintsCommand = Command<Queue<HintType>>()

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
        list.add(
            HomeCategory(
                SongCategory.LAST_PLAYED,
                stringProvider.getString(R.string.home_category_last_played),
                stringProvider.getString(R.string.home_category_last_played_subtitle),
                R.drawable.ic_category_last_played
            )
        )
        list.add(
            HomeCategory(
                SongCategory.LAST_ADDED,
                stringProvider.getString(R.string.home_category_new_songs),
                stringProvider.getString(R.string.home_category_new_songs_subtitle),
                R.drawable.ic_category_new_songs
            )
        )
        list.add(
            HomeCategory(
                SongCategory.MY_SONGS,
                stringProvider.getString(R.string.home_category_my_songs),
                stringProvider.getString(R.string.home_category_my_songs_subtitle),
                R.drawable.ic_category_my_songs
            )
        )
        list.add(
            HomeCategory(
                SongCategory.FAVOURITE,
                stringProvider.getString(R.string.home_category_favourite),
                stringProvider.getString(R.string.home_category_favourite_subtitle),
                R.drawable.ic_category_favourite
            )
        )
        list.add(
            HomeCategory(
                SongCategory.PATRIOTIC,
                stringProvider.getString(R.string.home_category_patriotic),
                stringProvider.getString(R.string.home_category_patriotic_subtitle),
                R.drawable.ic_category_patriotic
            )
        )
        list.add(
            HomeCategory(
                SongCategory.BONFIRE,
                stringProvider.getString(R.string.home_category_bonfire),
                stringProvider.getString(R.string.home_category_bonfire_subtitle),
                R.drawable.ic_category_bonfire
            )
        )
        list.add(
            HomeCategory(
                SongCategory.ABROAD,
                stringProvider.getString(R.string.home_category_abroad),
                stringProvider.getString(R.string.home_category_abroad_subtitle),
                R.drawable.ic_category_abroad
            )
        )
        _categories.value = list
    }

    private fun fetchHintsConfig() {
        viewModelScope.launch(Dispatchers.IO) {
            var hints = hintsConfigRepository.getHintsConfig()
            val hintsList = LinkedList<HintType>()
            if (!hints.shakeHintShown) {
                hintsList.add(HintType.SHAKE_PHONE)
                hints = hints.copy(shakeHintShown = true)
            }
            if (!hints.supportDeveloperShown) {
                hintsList.add(HintType.SUPPORT_DEVELOPER)
                hints = hints.copy(supportDeveloperShown = true)
            }
            if (!hints.derussificationShown) {
                hintsList.add(HintType.DERUSSIFICATION)
                hints = hints.copy(derussificationShown = true)
            }
            hintsConfigRepository.updateHintsConfig(hints)
            showHintsCommand.postValue(hintsList)
        }
    }

    fun hintDismissed(hint: HintType) {
        when (hint) {
            HintType.DERUSSIFICATION -> viewModelScope.launch(Dispatchers.IO) { songRepository.derussify() }
            else -> {
                /*ignored*/
            }
        }
    }
}