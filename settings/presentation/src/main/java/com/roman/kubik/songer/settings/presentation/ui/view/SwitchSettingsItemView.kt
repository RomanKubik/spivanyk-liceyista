package com.roman.kubik.songer.settings.presentation.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.roman.kubik.songer.settings.presentation.R

class SwitchSettingsItemView: LinearLayout {

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, -1)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        inflate(context, R.layout.switch_settings_item, this)
    }

}