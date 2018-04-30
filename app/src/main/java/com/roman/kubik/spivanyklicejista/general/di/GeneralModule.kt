package com.roman.kubik.spivanyklicejista.general.di

import com.roman.kubik.spivanyklicejista.domain.formatting.LyricsFormattingInteractor
import com.roman.kubik.spivanyklicejista.domain.utils.ChordsRecognizer
import com.roman.kubik.spivanyklicejista.domain.utils.ChordsRemover
import com.roman.kubik.spivanyklicejista.domain.utils.MarkedChordsRecognizer
import com.roman.kubik.spivanyklicejista.utils.SpannableStringChordsCreator

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class GeneralModule {

    @Provides
    @Singleton
    fun getLyricsFormattingInteractor(chordsRecognizer: ChordsRecognizer,
                                      chordsRemover: ChordsRemover,
                                      spannableStringChordsCreator: SpannableStringChordsCreator) = LyricsFormattingInteractor(chordsRecognizer, chordsRemover, spannableStringChordsCreator)
}
