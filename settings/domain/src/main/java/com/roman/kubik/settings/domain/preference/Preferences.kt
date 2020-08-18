package com.roman.kubik.settings.domain.preference

data class Preferences(
        val selectedInstrument: Instrument,
        val uiMode: UiMode,
        val showChords: Boolean
)