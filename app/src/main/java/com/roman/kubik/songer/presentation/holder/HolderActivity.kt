package com.roman.kubik.songer.presentation.holder

import android.os.Bundle
import com.roman.kubik.songer.R
import com.roman.kubik.songer.general.di.ActivityComponent
import com.roman.kubik.songer.presentation.base.BaseActivity
import com.roman.kubik.songer.presentation.holder.di.HolderModule

class HolderActivity: BaseActivity(), HolderContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_holder)
    }

    override fun injectActivity(activityComponent: ActivityComponent) {
        activityComponent.holderComponent(HolderModule(this)).inject(this)
    }
}