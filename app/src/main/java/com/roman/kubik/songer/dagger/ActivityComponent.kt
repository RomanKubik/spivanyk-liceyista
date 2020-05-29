package com.roman.kubik.songer.dagger

import androidx.fragment.app.FragmentActivity
import com.roman.kubik.songer.ui.main.MainActivityViewModel
import com.roman.kubik.songer.navigation.NavigationService
import dagger.BindsInstance
import dagger.Component

/**
 * Activity component
 */
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
@ActivityScope
interface ActivityComponent {

    val navigationService: NavigationService
    val mainActivityViewModel: MainActivityViewModel

    @Component.Factory
    interface Factory {
        fun create(
                applicationComponent: ApplicationComponent,
                @BindsInstance activity: FragmentActivity
        ): ActivityComponent
    }
}