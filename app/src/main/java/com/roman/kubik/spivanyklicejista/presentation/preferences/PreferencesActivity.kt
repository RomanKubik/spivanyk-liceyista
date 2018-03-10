package com.roman.kubik.spivanyklicejista.presentation.preferences

import android.os.Bundle
import android.os.PersistableBundle
import com.roman.kubik.spivanyklicejista.R
import com.roman.kubik.spivanyklicejista.general.android.SpivanykApplication.Companion.component
import com.roman.kubik.spivanyklicejista.presentation.BaseActivity
import com.roman.kubik.spivanyklicejista.presentation.preferences.di.PreferencesModule
import javax.inject.Inject

/**
 * Created by kubik on 3/10/18.
 */
class PreferencesActivity : BaseActivity(), PreferencesContract.View {

    @Inject
    lateinit var presenter: PreferencesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_preferences)
        component.preferencesComponent(PreferencesModule(this)).inject(this)
    }
}