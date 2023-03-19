package com.roman.kubik.songer.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.roman.kubik.songer.home.ui.view.Home

@Composable
fun HomeScreen(viewModel: HomeFragmentViewModel = viewModel()) {
    val uiState = viewModel.homeUiState.observeAsState(HomeUiState.EMPTY)
    Home(categories = uiState.value.categories, onCategorySelected = viewModel::onCategorySelected)
}

data class HomeUiState(val categories: List<HomeCategory>) {
    companion object {
        val EMPTY = HomeUiState(categories = emptyList())
    }
}