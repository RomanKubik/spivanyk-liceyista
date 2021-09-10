package com.roman.kubik.songer.view.tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_tutorial.*

class TutorialDialogFragment : DialogFragment() {

    var dismissListener: DismissListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.Theme_TutorialDialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_tutorial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            actionImage.setImageResource(it.getInt(TUTORIAL_ICON))
            description.setText(it.getInt(TUTORIAL_TEXT))
        }
        gotItButton.setOnClickListener {
            this.dismiss()
            dismissListener?.onDismissed(tag)
        }
    }

    interface DismissListener {
        fun onDismissed(tag: String?)
    }

    companion object {

        private const val TUTORIAL_ICON = "tutorial_icon"
        private const val TUTORIAL_TEXT = "tutorial_text"

        fun getInstance(@DrawableRes icon: Int, @StringRes text: Int): TutorialDialogFragment {
            val dialog = TutorialDialogFragment()
            val args = Bundle()
            args.putInt(TUTORIAL_ICON, icon)
            args.putInt(TUTORIAL_TEXT, text)
            dialog.arguments = args
            return dialog
        }
    }
}