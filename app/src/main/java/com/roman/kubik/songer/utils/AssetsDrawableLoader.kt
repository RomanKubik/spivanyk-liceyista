package com.roman.kubik.songer.utils

import android.content.Context
import android.graphics.drawable.Drawable
import java.io.IOException
import javax.inject.Inject

class AssetsDrawableLoader @Inject constructor(val context: Context) {

    fun loadDrawable(path: String?) : Drawable? {
        // load image
        return try {
            // get input stream
            val ims = context.assets.open(path!!)
            // load image as Drawable
            Drawable.createFromStream(ims, null)
        } catch (ex: Exception) {
            null
        }

    }
}