package com.roman.kubik.songer.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.roman.kubik.songer.general.android.SpivanykApplication.Companion.component
import com.roman.kubik.songer.general.di.ActivityComponent
import com.roman.kubik.songer.general.di.ActivityModule

/**
 * Base activity for all application activities
 * Created by kubik on 1/20/18.
 */

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectActivity(component.getActivityComponent(ActivityModule(this)))

    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        ButterKnife.bind(this)
    }

    override fun setContentView(view: View?) {
        super.setContentView(view)
        ButterKnife.bind(this)
    }

    override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) {
        super.setContentView(view, params)
        ButterKnife.bind(this)
    }

    abstract fun injectActivity(activityComponent: ActivityComponent)
}
