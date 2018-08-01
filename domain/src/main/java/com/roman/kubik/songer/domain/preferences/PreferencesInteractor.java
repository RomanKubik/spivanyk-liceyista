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
}