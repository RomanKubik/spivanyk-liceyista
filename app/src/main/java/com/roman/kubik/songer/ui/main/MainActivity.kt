package com.roman.kubik.songer.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.roman.kubik.songer.R
import com.roman.kubik.songer.core.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            if (shouldUncheckFab(destination.id)) {
                randomFab.isChecked = false
            }
        }
        setupViews()
        viewModel.create(navHostFragment)
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
            viewModel.navigateToRandomSong()
        }
    }
}
