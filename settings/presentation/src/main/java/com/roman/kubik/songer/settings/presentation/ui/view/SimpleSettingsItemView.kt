package com.roman.kubik.songer.settings.presentation.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.roman.kubik.songer.settings.presentation.R

open class SimpleSettingsItemView : SettingsItemView {

    private val settingsItemTitleView: TextView
        get() = findViewById(R.id.settingsItemTitle)

    private val settingsItemValueView: TextView
        get() = findViewById(R.id.settingsItemValue)

    override val layoutId: Int
            get() = R.layout.simple_settings_item

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, -1)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)


    override fun setSettingsTitle(res: Int) {
        settingsItemTitleView.setText(res)
    }

    override fun setSettingsTitle(title: String?) {
        settingsItemTitleView.text = title
    }

    override fun setSettingsValue(res: Int) {
        settingsItemValueView.setText(res)
    }

    override fun setSettingsValue(value: String?) {
        settingsItemValueView.text = value
    }

}