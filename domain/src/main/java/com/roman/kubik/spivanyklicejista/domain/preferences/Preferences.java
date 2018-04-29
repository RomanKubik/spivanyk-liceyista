package com.roman.kubik.spivanyklicejista.domain.preferences;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface Preferences {

    Single<Boolean> isChordsVisible();

    Completable setChordsVisible(boolean visible);


}
