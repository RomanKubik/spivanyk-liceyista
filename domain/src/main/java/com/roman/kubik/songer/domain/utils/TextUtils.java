package com.roman.kubik.songer.domain.utils;

public final class TextUtils {

    private TextUtils() {
    }

    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }
}
