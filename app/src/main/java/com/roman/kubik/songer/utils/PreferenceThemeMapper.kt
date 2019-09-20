package com.roman.kubik.songer.utils

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.roman.kubik.songer.R
import javax.inject.Inject

class PreferenceThemeMapper @Inject constructor(private val context: Context)  {

    fun mapThemePreference(theme: String): Int {
        val resources = context.resources
        return when(theme) {
            resources.getStringArray(R.array.theme_key)[0] -> AppCompatDelegate.MODE_NIGHT_NO
            resources.getStringArray(R.array.theme_key)[1] -> AppCompatDelegate.MODE_NIGHT_YES
            else -> if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM else AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
        }
    }
}