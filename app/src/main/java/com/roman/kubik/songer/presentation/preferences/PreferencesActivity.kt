package com.roman.kubik.songer.presentation.preferences

import android.os.Bundle
import android.preference.PreferenceFragment
import com.roman.kubik.songer.R
import com.roman.kubik.songer.general.android.SpivanykApplication.Companion.component
import com.roman.kubik.songer.presentation.BaseActivity
import com.roman.kubik.songer.presentation.preferences.di.PreferencesModule
import kotlinx.android.synthetic.main.activity_preferences.*
import javax.inject.Inject

/**
 * Created by kubik on 3/10/18.
 */
class PreferencesActivity : BaseActivity(), PreferencesContract.View {

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

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    private fun init() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.settings)
    }


    class AppPreferences : PreferenceFragment() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.app_preferences)
        }
    }

}