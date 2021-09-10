package com.roman.kubik.songer.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.Checkable
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FabBottomMenuItem @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FloatingActionButton(context, attrs, defStyleAttr), Checkable {

    private var checked: Boolean = false


    override fun setChecked(checked: Boolean) {
        this.checked = checked
        imageTintList?.let {
            val color = if (checked) {
                val a = IntArray(1)
                a[0] = android.R.attr.state_checked
                it.getColorForState(a, it.defaultColor)
            } else {
                it.defaultColor
            }
            drawable.setTint(color)
        }
    }

    override fun isChecked(): Boolean {
        return checked
    }

    override fun toggle() {
        isChecked = !checked
    }

}