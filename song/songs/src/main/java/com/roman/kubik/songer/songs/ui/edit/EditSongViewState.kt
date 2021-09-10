package com.roman.kubik.songer.songs.ui.edit

import com.roman.kubik.songer.songs.domain.song.Song

sealed class EditSongViewState
object LoadingState : EditSongViewState()
object EmptyTitle : EditSongViewState()
object EmptyLyrics : EditSongViewState()
object NewSongState : EditSongViewState()
data class ErrorState(val throwable: Throwable) : EditSongViewState()
data class EditSongState(val song: Song) : EditSongViewState()