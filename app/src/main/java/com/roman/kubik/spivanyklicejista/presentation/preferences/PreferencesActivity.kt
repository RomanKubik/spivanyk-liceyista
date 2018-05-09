package com.roman.kubik.spivanyklicejista.presentation.preferences

import android.os.Bundle
import android.preference.PreferenceFragment
import com.roman.kubik.spivanyklicejista.R
import com.roman.kubik.spivanyklicejista.general.android.SpivanykApplication.Companion.component
import com.roman.kubik.spivanyklicejista.presentation.BaseActivity
import com.roman.kubik.spivanyklicejista.presentation.preferences.di.PreferencesModule
import kotlinx.android.synthetic.main.activity_preferences.*
import javax.inject.Inject

/**
 * Created by kubik on 3/10/18.
 */
class PreferencesActivity : BaseActivity() {

    @Inject
    lateinit var presenter: PreferencesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        component.preferencesComponent(PreferencesModule(this)).inject(this)
        fragmentManager?.beginTransaction()
                ?.replace(R.id.preferencesContainer, AppPreferences())
                ?.commit()
        init()
    }

    private fun init() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }


    class AppPreferences : PreferenceFragment() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.app_preferences)

        }
    }

}