package com.roman.kubik.songer.songs.ui.details

import com.roman.kubik.settings.domain.preference.Preferences
import com.roman.kubik.songer.chords.model.Chord
import com.roman.kubik.songer.songs.domain.song.Song

sealed class SongDetailsViewState
object LoadingState : SongDetailsViewState()
data class ErrorState(val throwable: Throwable) : SongDetailsViewState()
data class SuccessState(val song: Song, val chords: List<Chord>, val preferences: Preferences) : SongDetailsViewState()