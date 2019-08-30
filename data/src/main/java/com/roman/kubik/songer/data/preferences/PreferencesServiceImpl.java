package com.roman.kubik.songer.data.preferences;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.roman.kubik.songer.data.chord.Instruments;
import com.roman.kubik.songer.data.preferences.mapper.PreferencesModelMapper;
import com.roman.kubik.songer.data.preferences.model.PreferencesModel;
import com.roman.kubik.songer.domain.preferences.Preferences;
import com.roman.kubik.songer.domain.preferences.PreferencesService;
import com.roman.kubik.songer.domain.preferences.TutorialPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Single;

@Singleton
public class PreferencesServiceImpl implements PreferencesService {

    private final SharedPreferences sharedPreferences;
    private final Gson gson;
    private final PreferencesModelMapper preferencesMapper;

    @Inject
    public PreferencesServiceImpl(SharedPreferences sharedPreferences, Gson gson, PreferencesModelMapper preferencesMapper) {
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
        this.preferencesMapper = preferencesMapper;
    }

    @Override
    public Single<Preferences> getPreferences() {
        PreferencesModel preferencesModel
                = gson.fromJson(sharedPreferences.getString(Keys.PREFERENCES, ""), PreferencesModel.class);
        return Single.just(preferencesMapper.fromEntity(preferencesModel));
    }

    @Override
    public Completable updatePreferences(Preferences preferences) {
        return Completable.fromAction(
                sharedPreferences.edit()
                        .putString(Keys.PREFERENCES, gson.toJson(preferencesMapper.toEntity(preferences)))
                        ::commit);
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
