package com.roman.kubik.songer.domain.preferences;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface Preferences {

    Single<Boolean> isChordsVisible();

    Completable setChordsVisible(boolean visible);

    Single<String> selectedInstrument();
}
