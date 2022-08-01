package com.roman.kubik.songer.settings.presentation.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.roman.kubik.songer.settings.presentation.R

abstract class SettingsItemView : ConstraintLayout {

    protected abstract val layoutId: Int

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, -1)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        inflate(context, layoutId, this)
        context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.SettingsItemView,
                0, 0).apply {
            setSettingsTitle(getString(R.styleable.SettingsItemView_settingsTitle))
            setSettingsValue(getString(R.styleable.SettingsItemView_settingsValue))
        }.recycle()

    }

    abstract fun setSettingsTitle(@StringRes res: Int)
    abstract fun setSettingsTitle(title: String?)
    abstract fun setSettingsValue(@StringRes res: Int)
    abstract fun setSettingsValue(value: String?)
}