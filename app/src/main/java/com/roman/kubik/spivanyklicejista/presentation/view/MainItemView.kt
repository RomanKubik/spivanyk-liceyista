package com.roman.kubik.spivanyklicejista.presentation.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import com.roman.kubik.spivanyklicejista.R
import kotlinx.android.synthetic.main.item_group_selection.view.*
import android.content.res.TypedArray


class MainItemView : ConstraintLayout {


    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
        initAttributes(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
        initAttributes(context, attrs)
    }

    private fun init() {
        View.inflate(context, R.layout.item_group_selection, this)
    }

    private fun initAttributes(context: Context, attrs: AttributeSet?) {
        val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.MainItemView,
                0, 0)

        try {
            try {
                setImage(a.getResourceId(R.styleable.MainItemView_image, R.color.hot_pink))
            } catch (e: IllegalStateException) {
                /* ignored */
            }
            try {
                setTitle(a.getString(R.styleable.MainItemView_title))
            } catch (e: IllegalStateException) {
                /* ignored */
            }
            try {
                setDescription(a.getString(R.styleable.MainItemView_description))
            } catch (e: IllegalStateException) {
                /* ignored */
            }
        } finally {
            a.recycle()
        }
    }

    fun setTitle(title: String) {
        categoryTitle.text = title
    }

    fun setImage(resId: Int) {
        imageView.setImageResource(resId)
    }

    fun setDescription(description: String) {
        categoryDescription.text = description
    }

}
