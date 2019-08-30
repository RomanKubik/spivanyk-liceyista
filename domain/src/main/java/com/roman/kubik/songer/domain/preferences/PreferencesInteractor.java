package com.roman.kubik.songer.domain.preferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Single;

@Singleton
public class PreferencesInteractor {

    private PreferencesService preferencesService;

    @Inject
    public PreferencesInteractor(PreferencesService preferencesService) {
        this.preferencesService = preferencesService;
    }

    public Single<Preferences> getPreferences() {
        return preferencesService.getPreferences();
    }

    public Completable updatePreferences(Preferences preferences) {
        return preferencesService.updatePreferences(preferences);
    }

    public Single<Boolean> isChordsVisible() {
        return preferencesService.isChordsVisible();
    }

    public Completable switchChordsVisibility() {
        return preferencesService.isChordsVisible()
                .flatMapCompletable(visible -> preferencesService.setChordsVisible(!visible));
    }

    public Single<String> selectedInstrument() {
        return preferencesService.selectedInstrument();
    }

    public Single<Boolean> isShakeTutorialShown() {
        return preferencesService.isShakeTutorialShown();
    }

    public Completable setShakeTutorialShown() {
        return preferencesService.setShakeTutorialShown(true);
    }

    public Single<Boolean> isAddSongTutorialShown() {
        return preferencesService.isAddSongTutorialShown();
    }

    public Completable setAddSongTutorialShown() {
        return preferencesService.setAddSongTutorialShown(true);
    }

    public Single<Boolean> isMarkChordsTutorialShown() {
        return preferencesService.isMarkChordsTutorialShown();
    }

    public Completable setMarkChordsTutorialShown() {
        return preferencesService.setMarkChordsTutorialShown(true);
    }

    public Single<Boolean> isDeleteTutorialShown() {
        return preferencesService.isDeleteTutorialShown();
    }

    public Completable setDeleteTutorialShown() {
        return preferencesService.setDeleteTutorialShown(true);
    }
}
