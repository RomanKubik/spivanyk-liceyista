package com.roman.kubik.songer.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.roman.kubik.songer.activityComponent

abstract class BaseActivity: AppCompatActivity() {

    abstract val viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigationService = activityComponent.navigationService
    }
}