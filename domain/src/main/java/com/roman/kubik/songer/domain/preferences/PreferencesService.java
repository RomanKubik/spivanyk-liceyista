package com.roman.kubik.songer.domain.preferences;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface PreferencesService {

    Single<Preferences> getPreferences();

    Completable updatePreferences(Preferences preferences);
}
