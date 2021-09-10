package com.roman.kubik.songer.core.ui.utils

import android.content.Context
import android.content.res.Configuration
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.Drawable

object AssetImageLoader {

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

    fun loadAsset(context: Context, imagePath: String?): Drawable? {
        // load image
        return try {
            // get input stream
            val ims = context.assets.open(imagePath!!)
            // load image as Drawable
            val d = Drawable.createFromStream(ims, null)
            if (isDarkMode(context)) {
                d.colorFilter = ColorMatrixColorFilter(INVERT_COLOR_MTX)
            }
            return d
        } catch (ex: Exception) {
            null
        }
    }

    private fun isDarkMode(context: Context): Boolean {
        return when (context.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> true
            else -> false
        }
    }
}