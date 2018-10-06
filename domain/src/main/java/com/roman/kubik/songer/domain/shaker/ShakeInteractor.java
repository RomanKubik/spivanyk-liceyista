package com.roman.kubik.songer.domain.shaker;

import com.roman.kubik.songer.domain.navigation.NavigationInteractor;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class ShakeInteractor {

    private static final int SHAKE_DEBOUNCE_TIMEOUT = 350;
    private final ShakeDetector shakeDetector;
    private NavigationInteractor navigationInteractor;

    @Inject
    public ShakeInteractor(ShakeDetector shakeDetector) {
        this.shakeDetector = shakeDetector;
        startListenShacking();
    }

    public void setNavigationInteractor(NavigationInteractor navigationInteractor) {
        this.navigationInteractor = navigationInteractor;
    }

    private void startListenShacking() {
        Disposable disposable = shakeDetector.shake()
                .debounce(SHAKE_DEBOUNCE_TIMEOUT, TimeUnit.MILLISECONDS)
                .subscribe(e -> {
                    if (navigationInteractor != null)
                        navigationInteractor.toRandomSong();
                });
    }
}
