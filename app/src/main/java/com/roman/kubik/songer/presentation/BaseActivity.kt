package com.roman.kubik.songer.presentation

import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife

/**
 * Base activity for all application activities
 * Created by kubik on 1/20/18.
 */

abstract class BaseActivity : AppCompatActivity() {

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
}
