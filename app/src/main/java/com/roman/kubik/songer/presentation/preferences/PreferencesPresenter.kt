package com.roman.kubik.songer.presentation.preferences

import androidx.appcompat.app.AppCompatDelegate
import com.roman.kubik.songer.data.database.DatabaseManager
import com.roman.kubik.songer.domain.chord.ChordInteractor
import com.roman.kubik.songer.domain.navigation.NavigationInteractor
import com.roman.kubik.songer.domain.preferences.Preferences
import com.roman.kubik.songer.domain.preferences.PreferencesInteractor
import com.roman.kubik.songer.domain.user.User
import com.roman.kubik.songer.domain.user.UserInteractor
import com.roman.kubik.songer.general.di.ActivityScope
import com.roman.kubik.songer.utils.PreferenceThemeMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by kubik on 3/10/18.
 */
@ActivityScope
class PreferencesPresenter @Inject constructor(private val view: PreferencesContract.View,
                                               private val preferencesInteractor: PreferencesInteractor,
                                               private val chordInteractor: ChordInteractor,
                                               private val navigationInteractor: NavigationInteractor,
                                               private val databaseManager: DatabaseManager,
                                               private val userInteractor: UserInteractor,
                                               private val preferenceThemeMapper: PreferenceThemeMapper,
                                               private val cd: CompositeDisposable)
    : PreferencesContract.Presenter {

    private var user: User? = null

    init {
        cd.add(userInteractor.user
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { user = it }
                .subscribe(view::showUser))
    }

    override fun destroy(preferences: Preferences) {
        cd.add(preferencesInteractor
                .preferences
                .map {
                    it.isChordsVisible = preferences.isChordsVisible
                    it.selectedInstrument = preferences.selectedInstrument
                    it.selectedTheme = preferences.selectedTheme
                    return@map it
                }
                .flatMapCompletable(preferencesInteractor::updatePreferences)
                .doOnComplete {
                    chordInteractor.updateChordRepository()
                    userInteractor.refreshUser()
                }
                .subscribe({ cd.dispose() }, {}))

    }

    override fun reset() {
        cd.add(databaseManager.reset()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    view.showResetError()
                }
                .subscribe(navigationInteractor::restart))
    }

    override fun setTheme(themeMode: String) {
        AppCompatDelegate.setDefaultNightMode(preferenceThemeMapper.mapThemePreference(themeMode))
    }

    override fun signIn() {
        if (!user?.email.isNullOrEmpty()) {
            userInteractor.logOut()
        } else {
            navigationInteractor.toSignIn(PreferencesContract.CODE_SIGN_IN)
        }
    }

    override fun onProfileUpdated() {
        userInteractor.refreshUser()
    }
}
