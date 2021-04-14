package com.roman.kubik.settings.domain.repository

import com.roman.kubik.settings.domain.hint.HintService
import com.roman.kubik.settings.domain.hint.Hints
import javax.inject.Inject

class HintsConfigRepository @Inject constructor(private val hintService: HintService) {

    suspend fun getHintsConfig(): Hints {
        return hintService.getHintsStatus()
    }

    suspend fun updateHintsConfig(hints: Hints) {
        hintService.updateHintsStatus(hints)
    }

}