package com.roman.kubik.songer.domain.shaker;

import io.reactivex.Observable;

public interface ShakeDetector {

    Observable<ShakeEvent> shake();
}
