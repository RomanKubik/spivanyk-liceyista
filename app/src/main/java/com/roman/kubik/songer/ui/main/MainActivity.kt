package com.roman.kubik.songer.ui.main

import android.os.Bundle
import com.roman.kubik.songer.activityComponent
import com.roman.kubik.songer.ui.base.BaseActivity
import com.roman.kubik.songer.dagger.viewModel

/**
 * Main activity. Application entry point
 */
class MainActivity : BaseActivity() {

    override val viewModel by viewModel { activityComponent.mainActivityViewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.toMainScreen()
    }
}