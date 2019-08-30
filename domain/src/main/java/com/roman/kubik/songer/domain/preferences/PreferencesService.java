package com.roman.kubik.songer.domain.preferences;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface PreferencesService {

    Single<Preferences> getPreferences();

    Completable updatePreferences(Preferences preferences);

    Single<Boolean> isChordsVisible();

    Completable setChordsVisible(boolean visible);

    Single<String> selectedInstrument();

    Single<Boolean> isShakeTutorialShown();
    Completable setShakeTutorialShown(boolean shown);
    Single<Boolean> isAddSongTutorialShown();
    Completable setAddSongTutorialShown(boolean shown);
    Single<Boolean> isMarkChordsTutorialShown();
    Completable setMarkChordsTutorialShown(boolean shown);
    Single<Boolean> isDeleteTutorialShown();
    Completable setDeleteTutorialShown(boolean shown);
}
