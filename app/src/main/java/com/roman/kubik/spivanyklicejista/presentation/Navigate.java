package com.roman.kubik.spivanyklicejista.presentation;

import android.app.Activity;
import android.content.Intent;

import com.roman.kubik.spivanyklicejista.presentation.main.MainActivity;

/**
 * Created by kubik on 1/14/18.
 */

public final class Navigate {

    public static void toMainActivty(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }
}
