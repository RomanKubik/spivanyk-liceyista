package com.roman.kubik.songer.presentation.main

import com.roman.kubik.songer.domain.category.Category
import com.roman.kubik.songer.domain.favourite.FavouriteInteractor
import com.roman.kubik.songer.domain.navigation.NavigationInteractor
import com.roman.kubik.songer.domain.preferences.PreferencesInteractor
import com.roman.kubik.songer.domain.song.SongInteractor
import com.roman.kubik.songer.general.di.ActivityScope
import com.roman.kubik.songer.presentation.tutorial.TutorialType
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class MainPresenter @Inject
constructor(private val view: MainContract.View,
            private val navigationInteractor: NavigationInteractor,
            private val songInteractor: SongInteractor,
            private val favouriteInteractor: FavouriteInteractor,
            private val preferencesInteractor: PreferencesInteractor,
            private val compositeDisposable: CompositeDisposable) : MainContract.Presenter {

    override fun requestData() {
        compositeDisposable.addAll(
                songInteractor.getCountByCategory(Category.PATRIOTIC_ID)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::setPatrioticsCount, view::showError),
                songInteractor.getCountByCategory(Category.BONFIRE_ID)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::setBonfiresCount, view::showError),
                songInteractor.getCountByCategory(Category.ABROAD_ID)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::setAbroadsCount, view::showError),
                songInteractor.count
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::setAllCount, view::showError),
                favouriteInteractor.count
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::setFavouriteCount, view::showError),
                preferencesInteractor.isAddSongTutorialShown
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            if (!it)
                                view.showTutorial(TutorialType.TYPE_ADD_SONG)
                            else
                                tutorialShown(TutorialType.TYPE_ADD_SONG)
                        }, view::showError)
        )
    }

    override fun requestRandom() = navigationInteractor.toRandomSong()

    override fun selectCategory(categoryId: Int) = navigationInteractor.toListActivity(categoryId)

    override fun addSong() = navigationInteractor.toAddSongActivity()

    override fun showSettings() = navigationInteractor.toPreferencesActivity()

    override fun tutorialShown(type: TutorialType) {
        when (type) {
            TutorialType.TYPE_ADD_SONG -> {
                compositeDisposable.addAll(
                        preferencesInteractor.setAddSongTutorialShown()
                                .subscribe(),
                        preferencesInteractor.isShakeTutorialShown
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    if (!it)
                                        view.showTutorial(TutorialType.TYPE_SHAKE)
                                    else
                                        tutorialShown(TutorialType.TYPE_SHAKE)
                                }, view::showError)
                )
            }
            TutorialType.TYPE_SHAKE -> compositeDisposable.add(preferencesInteractor.setShakeTutorialShown().subscribe())
            else -> {
            }
        }
    }

    override fun destroy() = compositeDisposable.clear()

}