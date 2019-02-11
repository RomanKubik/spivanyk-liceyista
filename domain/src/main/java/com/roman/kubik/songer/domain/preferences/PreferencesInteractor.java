package com.roman.kubik.songer.domain.preferences;

import io.reactivex.Completable;
import io.reactivex.Single;

public class PreferencesInteractor {

    private Preferences preferences;

    public PreferencesInteractor(Preferences preferences) {
        this.preferences = preferences;
    }

    public Single<Boolean> isChordsVisible() {
        return preferences.isChordsVisible();
    }

    public Completable switchChordsVisibility() {
        return preferences.isChordsVisible()
                .flatMapCompletable(visible -> preferences.setChordsVisible(!visible));
    }

    public Single<String> selectedInstrument() {
        return preferences.selectedInstrument();
    }

    public Single<Boolean> isShakeTutorialShown() {
        return preferences.isShakeTutorialShown();
    }

    public Completable setShakeTutorialShown() {
        return preferences.setShakeTutorialShown(true);
    }

    public Single<Boolean> isAddSongTutorialShown() {
        return preferences.isAddSongTutorialShown();
    }

    public Completable setAddSongTutorialShown() {
        return preferences.setAddSongTutorialShown(true);
    }

    public Single<Boolean> isMarkChordsTutorialShown() {
        return preferences.isMarkChordsTutorialShown();
    }

    public Completable setMarkChordsTutorialShown() {
        return preferences.setMarkChordsTutorialShown(true);
    }

    public Single<Boolean> isDeleteTutorialShown() {
        return preferences.isDeleteTutorialShown();
    }

    public Completable setDeleteTutorialShown() {
        return preferences.setDeleteTutorialShown(true);
    }
}
