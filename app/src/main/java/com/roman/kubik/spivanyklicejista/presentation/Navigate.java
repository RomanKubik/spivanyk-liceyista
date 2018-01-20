package com.roman.kubik.spivanyklicejista.presentation;

import android.app.Activity;
import android.content.Intent;

import com.roman.kubik.spivanyklicejista.presentation.main.MainActivity;

/**
 * Application router. Provides methods to navigate from one activity to another
 * Created by kubik on 1/14/18.
 */

public final class Navigate {

    public static void toMainActivity(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }
}
