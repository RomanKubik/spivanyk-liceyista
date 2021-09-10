package com.roman.kubik.songer.app.themes

import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.roman.kubik.settings.domain.preference.UiMode
import com.roman.kubik.settings.domain.theme.ThemeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ThemeServiceImpl @Inject constructor() : ThemeService {
    override fun applyUiMode(uiMode: UiMode) {
        GlobalScope.launch(Dispatchers.Main) {
            val nightMode = when (uiMode) {
                UiMode.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                UiMode.DARK -> AppCompatDelegate.MODE_NIGHT_YES
                UiMode.SYSTEM_DEFAULT ->
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q)
                        AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
                    else
                        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }

            AppCompatDelegate.setDefaultNightMode(nightMode)
        }
    }

}