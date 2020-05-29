package com.roman.kubik.songer.ui.main

import com.roman.kubik.songer.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * ViewModel of {@link MainActivity}
 */
class MainActivityViewModel @Inject constructor() : BaseViewModel() {

    fun toMainScreen() {
        navigationService.toMainScreen()
    }
}