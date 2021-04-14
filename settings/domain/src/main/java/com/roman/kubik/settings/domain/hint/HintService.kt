package com.roman.kubik.settings.domain.hint

interface HintService {

    suspend fun getHintsStatus(): Hints

    suspend fun updateHintsStatus(hints: Hints)

}