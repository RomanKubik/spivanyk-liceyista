package com.roman.kubik.settings.domain.theme

import com.roman.kubik.settings.domain.preference.UiMode

interface ThemeService {

    fun applyUiMode(uiMode: UiMode)
}