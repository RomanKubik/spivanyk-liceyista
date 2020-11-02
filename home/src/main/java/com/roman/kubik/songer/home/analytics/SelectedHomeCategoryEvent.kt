package com.roman.kubik.songer.home.analytics

import com.roman.kubik.songer.analytics.core.event.Event
import com.roman.kubik.songer.home.ui.HomeCategory

class SelectedHomeCategoryEvent(category: HomeCategory) : Event() {
    override val name: String = "select_category"
    override val attributes: Map<String, String> = mapOf("category" to category.songCategory.name)
}