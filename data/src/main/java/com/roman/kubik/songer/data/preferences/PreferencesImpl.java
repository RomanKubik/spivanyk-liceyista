package com.roman.kubik.songer.data.preferences;

import android.content.SharedPreferences;

import com.roman.kubik.songer.data.chord.Instruments;
import com.roman.kubik.songer.domain.preferences.Preferences;

import io.reactivex.Completable;
import io.reactivex.Single;

public class PreferencesImpl implements Preferences {

    private SharedPreferences sharedPreferences;

    public PreferencesImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Single<Boolean> isChordsVisible() {
        return Single.just(sharedPreferences.getBoolean(Keys.SHOW_CHORDS, true));
    }

    @Override
    public Completable setChordsVisible(boolean visible) {
        return Completable.fromAction(sharedPreferences.edit().putBoolean(Keys.SHOW_CHORDS, visible)::commit);
    }

    @Override
    public Single<String> selectedInstrument() {
        return Single.just(sharedPreferences.getString(Keys.SELECTED_INSTRUMENT, Instruments.GUITAR));
    }

    @Override
    public Single<Boolean> isShakeTutorialShown() {
        return Single.just(sharedPreferences.getBoolean(Keys.TUTORIAL_SHAKE, false));
    }

    @Override
    public Completable setShakeTutorialShown(boolean shown) {
        return Completable.fromAction(sharedPreferences.edit().putBoolean(Keys.TUTORIAL_SHAKE, shown)::commit);
    }

    @Override
    public Single<Boolean> isAddSongTutorialShown() {
        return Single.just(sharedPreferences.getBoolean(Keys.TUTORIAL_ADD_SONG, false));
    }

    @Override
    public Completable setAddSongTutorialShown(boolean shown) {
        return Completable.fromAction(sharedPreferences.edit().putBoolean(Keys.TUTORIAL_ADD_SONG, shown)::commit);
    }

    @Override
    public Single<Boolean> isMarkChordsTutorialShown() {
        return Single.just(sharedPreferences.getBoolean(Keys.TUTORIAL_MARK_CHORDS, false));
    }

    @Override
    public Completable setMarkChordsTutorialShown(boolean shown) {
        return Completable.fromAction(sharedPreferences.edit().putBoolean(Keys.TUTORIAL_MARK_CHORDS, shown)::commit);
    }

    @Override
    public Single<Boolean> isDeleteTutorialShown() {
        return Single.just(sharedPreferences.getBoolean(Keys.TUTORIAL_DELETE_SONG, false));
    }

    @Override
    public Completable setDeleteTutorialShown(boolean shown) {
        return Completable.fromAction(sharedPreferences.edit().putBoolean(Keys.TUTORIAL_DELETE_SONG, shown)::commit);
    }
}
