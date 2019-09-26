package com.roman.kubik.songer.general.di

import com.roman.kubik.songer.domain.navigation.NavigationService
import com.roman.kubik.songer.navigation.NavigationServiceImpl
import com.roman.kubik.songer.presentation.base.BaseFragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: BaseFragment) {

    @Provides
    fun getFragment() = fragment

    @Provides
    fun navigationService(fragment: BaseFragment): NavigationService = NavigationServiceImpl(fragment.requireActivity())

}