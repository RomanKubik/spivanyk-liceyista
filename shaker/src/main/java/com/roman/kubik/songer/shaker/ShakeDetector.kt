package com.roman.kubik.songer.shaker

import kotlinx.coroutines.flow.Flow

interface ShakeDetector {

    fun shakeDetected(): Flow<ShakeEvent>

}