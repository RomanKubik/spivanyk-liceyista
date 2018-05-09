package com.roman.kubik.spivanyklicejista.data.preferences;

import android.content.SharedPreferences;

import com.roman.kubik.spivanyklicejista.domain.preferences.Preferences;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;

public class PreferencesImpl implements Preferences {

    private SharedPreferences sharedPreferences;
    private PublishSubject<String> preferenceKey = PublishSubject.create();
    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener =
            (sharedPreferences, key) -> preferenceKey.onNext(key);

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
    public Observable<String> preferenceChange() {
        return preferenceKey
                .doOnSubscribe(d -> sharedPreferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener))
                .doOnDispose(() -> sharedPreferences.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener));
    }
}
