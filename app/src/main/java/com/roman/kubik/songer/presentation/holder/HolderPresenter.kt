package com.roman.kubik.songer.presentation.holder

import javax.inject.Inject

class HolderPresenter @Inject constructor(private val view: HolderContract.View): HolderContract.Presenter {

//    override fun onCreated() {
//        compositeDisposable.add(
//                preferencesInteractor
//                        .preferences
//                        .map { it.selectedTheme }
//                        .map(preferenceThemeMapper::mapThemePreference)
//                        .onErrorReturnItem(preferenceThemeMapper.getDefaultTheme())
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(AppCompatDelegate::setDefaultNightMode, view::showError)
//        )
//    }
}