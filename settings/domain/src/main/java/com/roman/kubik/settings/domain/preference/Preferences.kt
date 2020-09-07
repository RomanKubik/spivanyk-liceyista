package com.roman.kubik.settings.domain.preference

import com.roman.kubik.provider.SongDataSource

data class Preferences(
        val selectedInstrument: Instrument,
        val uiMode: UiMode,
        val showChords: Boolean,
        val selectedSongDataSource: Set<SongDataSource>
)