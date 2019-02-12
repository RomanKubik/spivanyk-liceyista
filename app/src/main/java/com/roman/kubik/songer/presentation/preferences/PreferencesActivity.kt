package com.roman.kubik.songer.presentation.preferences

import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import com.roman.kubik.songer.R
import com.roman.kubik.songer.general.di.ActivityComponent
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
    private val preferences = AppPreferences()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        fragmentManager?.beginTransaction()
                ?.replace(R.id.preferencesContainer, preferences)
                ?.commit()
        init()
    }

    override fun injectActivity(activityComponent: ActivityComponent) {
        activityComponent.preferencesComponent(PreferencesModule(this)).inject(this)
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    private fun init() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.settings)
        preferences.addResetClickListener{
            presenter.reset()
        }
    }


    class AppPreferences : PreferenceFragment() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.app_preferences)
        }

        fun addResetClickListener(function: () -> Unit) {
            val myPref = findPreference("factory_reset")
            myPref?.setOnPreferenceClickListener{
                function.invoke()
                true
            }
        }
    }

}