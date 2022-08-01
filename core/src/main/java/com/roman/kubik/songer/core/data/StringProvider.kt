package com.roman.kubik.songer.core.data

import androidx.annotation.StringRes

interface StringProvider {

    fun getString(@StringRes res: Int): String

    fun getString(@StringRes res: Int, vararg params: Any): String
}