package com.roman.kubik.songer.songs.ui

import androidx.hilt.lifecycle.ViewModelInject
import com.roman.kubik.songer.core.Command
import com.roman.kubik.songer.core.ui.base.BaseViewModel

class SharedSongViewModel @ViewModelInject constructor(): BaseViewModel() {

    val songDeletedCommand = Command<Unit?>()
}