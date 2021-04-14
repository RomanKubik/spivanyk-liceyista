package com.roman.kubik.settings.hints

import android.content.Context
import android.content.SharedPreferences
import com.roman.kubik.settings.domain.hint.HintService
import com.roman.kubik.settings.domain.hint.Hints
import javax.inject.Inject

class PreferenceHintService @Inject constructor(context: Context) : HintService {

    companion object {
        const val HINT_PREFERENCES = "preferences.hint"

        const val HINT_SHAKE_KEY = "hint.shake"
        const val HINT_RECOGNIZER_KEY = "hint.recognizer"
    }

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(HINT_PREFERENCES, Context.MODE_PRIVATE)

    override suspend fun getHintsStatus(): Hints {
        return Hints(
                sharedPreferences.getBoolean(HINT_SHAKE_KEY, false),
                sharedPreferences.getBoolean(HINT_RECOGNIZER_KEY, false)
        )
    }

    override suspend fun updateHintsStatus(hints: Hints) {
        sharedPreferences.edit().apply {
            putBoolean(HINT_SHAKE_KEY, hints.shakeHintShown)
            putBoolean(HINT_RECOGNIZER_KEY, hints.chordsRecognizerShown)
        }.apply()
    }
}