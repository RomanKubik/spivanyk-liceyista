package com.roman.kubik.songer.general.di

import android.app.Activity
import com.roman.kubik.songer.domain.navigation.NavigationInteractor
import com.roman.kubik.songer.domain.navigation.NavigationService
import com.roman.kubik.songer.domain.shaker.ShakeInteractor
import com.roman.kubik.songer.domain.song.SongRepository
import com.roman.kubik.songer.navigation.NavigationServiceImpl
import com.roman.kubik.songer.utils.AssetsDrawableLoader
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    fun activity(): Activity {
        return activity
    }

    @Provides
    fun navigationService(activity: Activity): NavigationService = NavigationServiceImpl(activity)

    @Provides
    fun navigationInteractor(navigationService: NavigationService,
                             songRepository: SongRepository,
                             shakeInteractor: ShakeInteractor): NavigationInteractor {
        val navigationInteractor = NavigationInteractor(navigationService, songRepository)
        shakeInteractor.setNavigationInteractor(navigationInteractor)
        return navigationInteractor
    }

    @Provides
    fun getAssetsDrawableLoader(activity: Activity) = AssetsDrawableLoader(activity)
}