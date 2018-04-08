package com.roman.kubik.spivanyklicejista.general.di

import com.roman.kubik.spivanyklicejista.domain.utils.MarkedChordsRecognizer

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class GeneralModule {

    val markedChordsRecognizer: MarkedChordsRecognizer
        @Provides
        @Singleton
        get() = MarkedChordsRecognizer()
}
