package com.roman.kubik.songer.navigation.di

import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.roman.kubik.songer.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object NavigationModule {

    @Provides
    fun getNavController(fragment: FragmentActivity) = fragment.findNavController(R.id.fragmentContainerView)

}