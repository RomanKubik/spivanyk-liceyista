package com.roman.kubik.songer.settings.presentation.ui.view

import android.content.Context
import android.util.AttributeSet
import com.roman.kubik.songer.settings.presentation.R
import kotlinx.android.synthetic.main.simple_settings_item.view.*

open class SimpleSettingsItemView : SettingsItemView {

    override val layoutId: Int
            get() = R.layout.simple_settings_item

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, -1)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)


    override fun setSettingsTitle(res: Int) {
        settingsItemTitle.setText(res)
    }

    override fun setSettingsTitle(title: String?) {
        settingsItemTitle.text = title
    }

    override fun setSettingsValue(res: Int) {
        settingsItemValue.setText(res)
    }

    override fun setSettingsValue(value: String?) {
        settingsItemValue.text = value
    }

}