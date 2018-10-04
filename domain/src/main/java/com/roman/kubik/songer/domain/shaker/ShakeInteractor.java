package com.roman.kubik.songer.domain.shaker;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ShakeInteractor {

    private static final int SHAKE_DEBOUNCE_TIMEOUT = 5000;
    private final ShakeDetector shakeDetector;

    @Inject
    public ShakeInteractor(ShakeDetector shakeDetector) {
        this.shakeDetector = shakeDetector;
    }

    public Observable<ShakeEvent> shacking() {
        return shakeDetector.shake()
                .debounce(SHAKE_DEBOUNCE_TIMEOUT, TimeUnit.MILLISECONDS);
    }
}
