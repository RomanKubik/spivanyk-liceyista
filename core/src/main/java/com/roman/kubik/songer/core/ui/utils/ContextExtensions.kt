package com.roman.kubik.songer.core.ui.utils

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes

fun Context.getAttributeColor(@AttrRes colorAttrResId: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(colorAttrResId, typedValue, true)
    return typedValue.data
}