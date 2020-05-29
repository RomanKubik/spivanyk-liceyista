package com.roman.kubik.songer.navigation

import androidx.fragment.app.FragmentActivity
import javax.inject.Inject

/**
 * Implementation of {@link NavigationService}
 */
class NavigationServiceImpl @Inject constructor(private val activity: FragmentActivity) :
    NavigationService {

    override fun toMainScreen() {
    }
}