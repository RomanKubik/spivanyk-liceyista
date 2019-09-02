package com.roman.kubik.songer.domain.chord;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface Instruments {

    String GUITAR = "guitar";
    String UKULELE = "ukulele";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({GUITAR, UKULELE})
    @interface Instrument {
    }

}
