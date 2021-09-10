package com.roman.kubik.songer.songs.ui

import com.roman.kubik.songer.core.Command
import com.roman.kubik.songer.core.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedSongViewModel @Inject constructor(): BaseViewModel() {

    val songDeletedCommand = Command<Unit?>()
}