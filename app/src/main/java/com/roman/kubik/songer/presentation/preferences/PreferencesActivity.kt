package com.roman.kubik.songer.presentation.preferences

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.TwoStatePreference
import butterknife.OnClick
import com.roman.kubik.songer.R
import com.roman.kubik.songer.domain.preferences.Preferences
import com.roman.kubik.songer.domain.user.User
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
        supportFragmentManager.beginTransaction()
                .replace(R.id.preferencesContainer, preferences)
                .commit()
        init()
    }

    override fun onStart() {
        super.onStart()
        preferences.addResetClickListener(this::showResetDialog)
        preferences.addThemeChangeListener(presenter::setTheme)
    }

    override fun injectActivity(activityComponent: ActivityComponent) {
        activityComponent.preferencesComponent(PreferencesModule(this)).inject(this)
    }

    override fun onDestroy() {
        presenter.destroy(generatePreferences())
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PreferencesContract.CODE_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                presenter.onProfileUpdated()
            } else {

            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> this.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showResetError() {

    }

    override fun showUser(user: User) {
//        if (user.email.isNullOrEmpty()) {
//            profileTitle.setText(R.string.preferences_sign_in_title)
//            profileDescription.setText(R.string.preferences_sign_in_description)
//        } else {
//            profileTitle.setText(R.string.preferences_you_are_signed_in)
//            profileDescription.text = user.email
//        }
//        Glide.with(this)
//                .load(user.picturePath)
//                .apply(RequestOptions.circleCropTransform())
//                .error(R.drawable.ic_person)
//                .into(profileImage)
    }

//    @OnClick(R.id.sectionProfile)
//    fun onProfileClicked() {
//        presenter.signIn()
//    }

    private fun init() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showResetDialog() {
        AlertDialog.Builder(this)
                .setTitle(R.string.ttl_factory_reset)
                .setMessage(R.string.msg_factory_reset_long)
                .setPositiveButton(R.string.reset) { _, _ ->
                    presenter.reset()
                }
                .setNegativeButton(R.string.discard) { _, _ ->
                }
                .show()
    }

    private fun generatePreferences(): Preferences {
        val pref = Preferences()
        pref.isChordsVisible = preferences.findPreference<TwoStatePreference>(getString(R.string.id_chord_visible))!!.isChecked
        pref.selectedInstrument = preferences.findPreference<ListPreference>(getString(R.string.id_selected_instrument))!!.value
        pref.selectedTheme = preferences.findPreference<ListPreference>(getString(R.string.id_selected_theme))!!.value
        return pref
    }


    class AppPreferences : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.app_preferences, rootKey)
        }

        fun addResetClickListener(function: () -> Unit) {
            val myPref: Preference? = findPreference(getString(R.string.id_factory_reset))
            myPref?.setOnPreferenceClickListener {
                function.invoke()
                true
            }
        }

        fun addThemeChangeListener(function: (nightMode: String) -> Unit) {
            findPreference<ListPreference>(getString(R.string.id_selected_theme))
                    ?.setOnPreferenceChangeListener { _, newValue ->
                        function.invoke(newValue as @kotlin.ParameterName(name = "nightMode") String)
                        true
                    }
        }

    }
}