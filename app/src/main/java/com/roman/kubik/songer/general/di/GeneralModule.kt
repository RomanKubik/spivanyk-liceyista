package com.roman.kubik.songer.general.di

import com.roman.kubik.songer.data.preferences.PreferencesServiceImpl
import com.roman.kubik.songer.domain.formatting.LyricsFormatter
import com.roman.kubik.songer.domain.formatting.LyricsFormattingInteractor
import com.roman.kubik.songer.domain.preferences.PreferencesService
import com.roman.kubik.songer.domain.utils.ChordsRecognizer
import com.roman.kubik.songer.domain.utils.ChordsRemover
import com.roman.kubik.songer.utils.SpannableStringChordsCreator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GeneralModule {

    @Provides
    @Singleton
    fun getLyricsFormatter(chordsRecognizer: ChordsRecognizer): LyricsFormatter = chordsRecognizer

    @Provides
    @Singleton
    fun getLyricsFormattingInteractor(chordsRecognizer: ChordsRecognizer,
                                      chordsRemover: ChordsRemover,
                                      spannableStringChordsCreator: SpannableStringChordsCreator) = LyricsFormattingInteractor(chordsRecognizer, chordsRemover, spannableStringChordsCreator)

    @Provides
    @Singleton
    fun getPreferencesService(preferencesServiceImpl: PreferencesServiceImpl): PreferencesService = preferencesServiceImpl
}
