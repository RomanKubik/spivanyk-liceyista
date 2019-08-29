package com.roman.kubik.songer.presentation.tutorial

import com.roman.kubik.songer.R

enum class TutorialType(val logoId: Int, val labelId: Int) {
    TYPE_ADD_SONG(R.drawable.ic_list_add_secondary, R.string.tutorial_add_song),
    TYPE_SHAKE(R.drawable.ic_shake, R.string.tutorial_shake_phone),
    TYPE_MARK_CHORDS(R.drawable.ic_transform_secondary, R.string.tutorial_mark_chords),
    TYPE_DELETE_SONG(R.drawable.ic_touch, R.string.tutorial_delete_song)
}