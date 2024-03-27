package com.roman.kubik.songer.settings.presentation.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.Checkable
import android.widget.CompoundButton
import android.widget.Switch
import com.roman.kubik.songer.settings.presentation.R

class SwitchSettingsItemView : SimpleSettingsItemView, Checkable {

    private val settingsItemSwitchView: Switch
        get() = findViewById(R.id.settingsItemSwitch)

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

    override fun isChecked() = settingsItemSwitchView.isChecked

    override fun toggle() {
        settingsItemSwitchView.toggle()
    }

    override fun setChecked(checked: Boolean) {
        settingsItemSwitchView.isChecked = checked
    }

    fun setOnCheckedChangeListener(listener: CompoundButton.OnCheckedChangeListener) {
        settingsItemSwitchView.setOnCheckedChangeListener(listener)
    }

}