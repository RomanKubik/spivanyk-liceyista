package com.roman.kubik.songer.presentation.edit

import com.roman.kubik.songer.domain.category.Category
import com.roman.kubik.songer.domain.formatting.LyricsFormattingInteractor
import com.roman.kubik.songer.domain.preferences.PreferencesInteractor
import com.roman.kubik.songer.domain.song.Song
import com.roman.kubik.songer.domain.song.SongInteractor
import com.roman.kubik.songer.presentation.tutorial.TutorialType
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class EditSongPresenter @Inject constructor(
        private val view: EditSongContract.View,
        private val songInteractor: SongInteractor,
        private val lyricsFormattingInteractor: LyricsFormattingInteractor,
        private val preferencesInteractor: PreferencesInteractor,
        private val compositeDisposable: CompositeDisposable) : EditSongContract.Presenter {

    private var song: Song? = null

    override fun fetchSong(songId: Int) {
        compositeDisposable.add(
                preferencesInteractor.isMarkChordsTutorialShown
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            if (!it)
                                view.showTutorial(TutorialType.TYPE_MARK_CHORDS)
                            else
                                tutorialShown(TutorialType.TYPE_MARK_CHORDS)
                        }, { view.showError(it!!.message!!) })
        )
        if (songId == -1) return
        view.showProgress(true)
        compositeDisposable.add(songInteractor.getById(songId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { view.showProgress(false) }
                .doOnSuccess { s -> song = s }
                .subscribe(view::showSong
                ) { t -> view.showError(t.message!!) })
    }

    override fun recognizeChords(lyrics: String) {
        view.onChordsRecognized(lyricsFormattingInteractor.recognizeChords(lyrics))
    }

    override fun saveSong(title: String, lyrics: String) {
        view.showProgress(true)
        if (song == null) {
            song = Song(UUID.randomUUID().hashCode(), title, lyrics, Category.USERS_ID)
        } else {
            song?.title = title
            song?.lyrics = lyrics
        }
        compositeDisposable.add(songInteractor.insertOrUpdate(song)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { view.showProgress(false) }
                .subscribe(view::onSongSaved
                ) { t -> view.showError(t.message!!) })
    }

    override fun tutorialShown(tutorialType: TutorialType) {
        when (tutorialType) {
            TutorialType.TYPE_MARK_CHORDS -> compositeDisposable.add(preferencesInteractor.setMarkChordsTutorialShown().subscribe())
            else -> {
            }
        }
    }

    override fun destroy() {
        compositeDisposable.clear()
    }
}