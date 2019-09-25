package com.roman.kubik.songer.utils

import android.content.Context
import androidx.annotation.PluralsRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.hasOpenDialog(): Boolean {
    val fragments = this.supportFragmentManager.fragments
    for (fragment in fragments) {
        if (fragment is DialogFragment) {
            return true
        }
    }

    return false
}

fun Context.getPluralString(@PluralsRes id: Int, count: Int): String {
    return String.format(resources.getQuantityString(id, count), count)
}