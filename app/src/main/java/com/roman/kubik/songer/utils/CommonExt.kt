package com.roman.kubik.songer.utils

import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentActivity

fun FragmentActivity.hasOpenDialog(): Boolean {
    val fragments = this.supportFragmentManager.fragments
    for (fragment in fragments) {
        if (fragment is DialogFragment) {
            return true
        }
    }

    return false
}