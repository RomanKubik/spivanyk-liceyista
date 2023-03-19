package com.roman.kubik.songer.home.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.roman.kubik.songer.home.R
import com.roman.kubik.songer.home.ui.HomeCategory
import com.roman.kubik.songer.songs.domain.song.SongCategory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(categories: List<HomeCategory>, onCategorySelected: (HomeCategory) -> Unit = {}) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = { Text(text = stringResource(id = R.string.app_name)) },
            )
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(categories) { index, category ->
                    HomeCategory(modifier = Modifier.clickable {
                        onCategorySelected.invoke(category)
                    }, category = category)
                    if (index != categories.size - 1) {
                        Divider(color = Color.Gray)
                    }
                }
            }
        }
    }

}


@Preview(name = "Hello")
@Composable
fun HomePreview() {
    Home(
        categories =
        listOf(
            HomeCategory(
                songCategory = SongCategory.BONFIRE,
                title = "Bonfire songs",
                subtitle = "Super duper subtitle",
                icon = R.drawable.ic_bonfire,
                iconBackgroundColor = R.color.bright_green
            ), HomeCategory(
                songCategory = SongCategory.PATRIOTIC,
                title = "PATRIOTIC songs",
                subtitle = "Super duper subtitle",
                icon = R.drawable.ic_flag,
                iconBackgroundColor = R.color.olive_green
            ), HomeCategory(
                songCategory = SongCategory.ABROAD,
                title = "ABROAD songs",
                subtitle = "Super duper subtitle",
                icon = R.drawable.ic_globe,
                iconBackgroundColor = R.color.aqua_marine
            ), HomeCategory(
                songCategory = SongCategory.LAST_PLAYED,
                title = "LAST_PLAYED songs",
                subtitle = "Super duper subtitle",
                icon = R.drawable.ic_history,
                iconBackgroundColor = R.color.hot_pink
            )
        )
    )
}