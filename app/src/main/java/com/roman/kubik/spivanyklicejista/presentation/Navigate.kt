package com.roman.kubik.spivanyklicejista.presentation

import android.app.Activity
import android.content.Intent

import com.roman.kubik.spivanyklicejista.presentation.main.MainActivity

/**
 * Application router. Provides methods to navigate from one activity to another
 * Created by kubik on 1/14/18.
 */

object Navigate {

    fun toMainActivity(activity: Activity) {
        activity.startActivity(Intent(activity, MainActivity::class.java))
    }
}
