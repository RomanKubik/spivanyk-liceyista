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

    public Completable switchChordsVisibility() {
        return preferencesService.getPreferences()
                .doOnSuccess(p -> p.setChordsVisible(!p.isChordsVisible()))
                .flatMapCompletable(this::updatePreferences);
    }

    public Completable setShakeTutorialShown() {
        return preferencesService.getPreferences()
                .doOnSuccess(p -> p.getTutorialPreferences().setShakeShown(true))
                .flatMapCompletable(this::updatePreferences);
    }

    public Completable setAddSongTutorialShown() {
        return preferencesService.getPreferences()
                .doOnSuccess(p -> p.getTutorialPreferences().setAddSongShown(true))
                .flatMapCompletable(this::updatePreferences);
    }

    public Completable setMarkChordsTutorialShown() {
        return preferencesService.getPreferences()
                .doOnSuccess(p -> p.getTutorialPreferences().setMarkChordsShown(true))
                .flatMapCompletable(this::updatePreferences);
    }

    public Completable setDeleteTutorialShown() {
        return preferencesService.getPreferences()
                .doOnSuccess(p -> p.getTutorialPreferences().setDeleteShown(true))
                .flatMapCompletable(this::updatePreferences);
    }
}
