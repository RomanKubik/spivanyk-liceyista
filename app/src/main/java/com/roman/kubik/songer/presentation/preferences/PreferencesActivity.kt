package com.roman.kubik.songer.presentation.preferences

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.preference.PreferenceFragmentCompat
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.roman.kubik.songer.R
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
        supportFragmentManager?.beginTransaction()
                ?.replace(R.id.preferencesContainer, preferences)
                ?.commit()
        init()
    }

    override fun onStart() {
        super.onStart()
        preferences.addResetClickListener(this::showResetDialog)
    }

    override fun injectActivity(activityComponent: ActivityComponent) {
        activityComponent.preferencesComponent(PreferencesModule(this)).inject(this)
    }

    override fun onDestroy() {
        presenter.destroy()
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

    override fun showResetError() {

    }

    override fun showUser(user: User) {
        if (user.email.isNullOrEmpty()) {
            profileTitle.setText(R.string.preferences_sign_in_title)
            profileDescription.setText(R.string.preferences_sign_in_description)
        } else {
            profileTitle.setText(R.string.preferences_you_are_signed_in)
            profileDescription.text = user.email
        }
        Glide.with(this)
                .load(user.picturePath)
                .apply(RequestOptions.circleCropTransform())
                .error(R.drawable.ic_person)
                .into(profileImage)
    }

    @OnClick(R.id.sectionProfile)
    fun onProfileClicked() {
        presenter.signIn()
    }

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


    class AppPreferences : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.app_preferences, rootKey)
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