package com.roman.kubik.songer.settings.presentation.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.Checkable
import android.widget.CompoundButton
import com.roman.kubik.songer.settings.presentation.R
import kotlinx.android.synthetic.main.switch_settings_item.view.*

class SwitchSettingsItemView : SimpleSettingsItemView, Checkable {

    override val layoutId: Int
        get() = R.layout.switch_settings_item

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, -1)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        setOnClickListener {
            toggle()
        }
    }

    override fun isChecked() = settingsItemSwitch.isChecked

    override fun toggle() {
        settingsItemSwitch.toggle()
    }

    override fun setChecked(checked: Boolean) {
        settingsItemSwitch.isChecked = checked
    }

    fun setOnCheckedChangeListener(listener: CompoundButton.OnCheckedChangeListener) {
        settingsItemSwitch.setOnCheckedChangeListener(listener)
    }

}