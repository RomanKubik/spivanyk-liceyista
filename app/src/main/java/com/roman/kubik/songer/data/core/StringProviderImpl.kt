package com.roman.kubik.songer.data.core

import android.content.Context
import com.roman.kubik.songer.core.data.StringProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StringProviderImpl @Inject constructor(
        @ApplicationContext private val context: Context
) : StringProvider {
    override fun getString(res: Int): String {
        return context.getString(res)
    }

    override fun getString(res: Int, vararg params: Any): String {
        return context.getString(res, params)
    }
}