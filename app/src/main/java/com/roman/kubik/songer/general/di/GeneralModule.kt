package com.roman.kubik.songer.general.di

import com.roman.kubik.songer.domain.formatting.LyricsFormattingInteractor
import com.roman.kubik.songer.domain.utils.ChordsRecognizer
import com.roman.kubik.songer.domain.utils.ChordsRemover
import com.roman.kubik.songer.domain.utils.MarkedChordsRecognizer
import com.roman.kubik.songer.utils.SpannableStringChordsCreator

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
