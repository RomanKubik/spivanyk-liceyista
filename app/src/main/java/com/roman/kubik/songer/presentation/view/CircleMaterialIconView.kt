package com.roman.kubik.songer.presentation.view

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.DrawableUtils
import androidx.appcompat.widget.TintTypedArray
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.widget.ImageViewCompat
import com.roman.kubik.songer.R


class CircleMaterialIconView : AppCompatImageView {

    private var mainColor: Int? = null
    private var bitmap: Bitmap? = null
    private val rect = RectF()
    private val paint = Paint()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = layoutParams.width - getPadding()
        val height = layoutParams.height - getPadding()
        bitmap = getBitmapFromVectorDrawable(drawable!!, width, height)
        mainColor = ResourcesCompat.getColor(resources, R.color.olive_green_20, context.theme)
    }

    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
        paint.color = mainColor!!
        rect.set(0f, 0f, layoutParams.width.toFloat(), layoutParams.height.toFloat())
        canvas?.drawOval(rect, paint)
        canvas?.drawBitmap(bitmap!!, getPadding().toFloat() / 2, getPadding().toFloat() / 2, null)
    }

    private fun getPadding() = resources.getDimensionPixelOffset(R.dimen.spacing_16)

    private fun getBitmapFromVectorDrawable(drawable: Drawable, width: Int, height: Int): Bitmap {
        var d = drawable
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            d = DrawableCompat.wrap(drawable).mutate()
        }

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        d.setBounds(0, 0, canvas.width, canvas.height)
        d.draw(canvas)

        return bitmap
    }
}