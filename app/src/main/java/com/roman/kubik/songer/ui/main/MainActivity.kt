package com.roman.kubik.songer.ui.main

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.roman.kubik.ads.google.GoogleAdsModule
import com.roman.kubik.songer.R
import com.roman.kubik.songer.core.ui.base.BaseActivity
import com.roman.kubik.songer.core.ui.base.FragmentScrollListener
import com.roman.kubik.songer.notification.NotificationManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@AndroidEntryPoint
class MainActivity : BaseActivity(), FragmentScrollListener {

    companion object {
        const val BOTTOM_NAVIGATION_ANIMATION_DURATION = 500L
    }

    private val viewModel: MainActivityViewModel by viewModels()
    private val bottomNavigationHeight by lazy {
        bottomNavigationView.height + randomFab.height / 2f
    }

    @Inject
    lateinit var googleAdsModule: GoogleAdsModule

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Songer)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        googleAdsModule.activity = this
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            if (shouldUncheckFab(destination.id)) {
                randomFab.isChecked = false
            }
        }
        setupViews()
        viewModel.create(navHostFragment)

        parseIntent()
    }

    private fun shouldUncheckFab(destinationId: Int): Boolean {
        return randomFab.isChecked
                && destinationId != R.id.songDetailsFragment
                && destinationId != R.id.editSongFragment
    }

    private fun setupViews() {
        randomFab.setOnClickListener {
            randomFab.isChecked = true
            bottomNavigationView.menu.findItem(R.id.menu_empty).isChecked = true
            viewModel.onSelectRandomSong()
        }
    }

    private fun parseIntent() {
        intent.extras?.apply {
            getInt(NotificationManager.NOTIFICATION_ID_EXTRA, -1).let(viewModel::dismissNotification)
        }
    }

    override fun onScrollUp() {
        showBottomNavigation()
    }

    override fun onScrollDown() {
        hideBottomNavigation()
    }

    private fun showBottomNavigation() {
        if (randomFab.translationY < bottomNavigationHeight) return
        ValueAnimator.ofFloat(bottomNavigationHeight, 0f)
                .setDuration(BOTTOM_NAVIGATION_ANIMATION_DURATION)
                .apply {
                    interpolator = DecelerateInterpolator()
                    addUpdateListener {
                        randomFab.translationY = it.animatedValue as Float
                        bottomNavigationView.translationY = it.animatedValue as Float
                    }
                }
                .start()
    }

    private fun hideBottomNavigation() {
        if (randomFab.translationY > 0) return
        ValueAnimator.ofFloat(0f, bottomNavigationHeight)
                .setDuration(BOTTOM_NAVIGATION_ANIMATION_DURATION)
                .apply {
                    interpolator = DecelerateInterpolator()
                    addUpdateListener {
                        randomFab.translationY = it.animatedValue as Float
                        bottomNavigationView.translationY = it.animatedValue as Float
                    }
                }
                .start()
    }
}
