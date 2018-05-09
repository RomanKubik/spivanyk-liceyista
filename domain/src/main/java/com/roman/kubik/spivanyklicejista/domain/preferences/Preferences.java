package com.roman.kubik.spivanyklicejista.domain.preferences;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface Preferences {

    Single<Boolean> isChordsVisible();

    Completable setChordsVisible(boolean visible);

    Observable<String> preferenceChange();

}
