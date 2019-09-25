package com.roman.kubik.songer.utils

import android.content.Context
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.Drawable
import com.roman.kubik.songer.R
import javax.inject.Inject

class AssetsDrawableLoader @Inject constructor(val context: Context) {


    fun loadDrawable(path: String?): Drawable? {
        // load image
        return try {
            // get input stream
            val ims = context.assets.open(path!!)
            // load image as Drawable
            val d = Drawable.createFromStream(ims, null)
            if (isDarkMode()) {
                d.colorFilter = ColorMatrixColorFilter(INVERT_COLOR_MTX)
            }
            return d
        } catch (ex: Exception) {
            null
        }
    }

    private fun isDarkMode(): Boolean {
        return !context.resources.getBoolean(R.bool.use_light_status)
    }

    companion object {
        /**
         * Color matrix that flips the components (<code>-1.0f * c + 255 = 255 - c</code>)
         * and keeps the alpha intact.
         */
        private val INVERT_COLOR_MTX: FloatArray = floatArrayOf(
                -1.0f, 0f, 0f, 0f, 255f, // red
                0f, -1.0f, 0f, 0f, 255f, // green
                0f, 0f, -1.0f, 0f, 255f, // blue
                0f, 0f, 0f, 1.0f, 0f  // alpha
        )
    }
}