package com.roman.kubik.songer.songs.ui.list

import com.roman.kubik.settings.domain.preference.Preferences
import com.roman.kubik.songer.songs.domain.song.Song

sealed class SongsListViewState
object LoadingState: SongsListViewState()
object NoSongsViewState: SongsListViewState()
data class ErrorState(val throwable: Throwable) : SongsListViewState()
data class SuccessState(val songs: List<Song>, val preferences: Preferences) : SongsListViewState()