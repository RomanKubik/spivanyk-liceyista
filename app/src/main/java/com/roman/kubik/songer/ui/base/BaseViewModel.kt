package com.roman.kubik.songer.ui.base

import androidx.lifecycle.ViewModel
import com.roman.kubik.songer.navigation.NavigationService

abstract class BaseViewModel: ViewModel() {

    lateinit var navigationService: NavigationService
}