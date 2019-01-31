package com.roman.kubik.songer.utils

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity

fun androidx.fragment.app.FragmentActivity.hasOpenDialog(): Boolean {
    val fragments = this.supportFragmentManager.fragments
    for (fragment in fragments) {
        if (fragment is androidx.fragment.app.DialogFragment) {
            return true
        }
    }

    return false
}