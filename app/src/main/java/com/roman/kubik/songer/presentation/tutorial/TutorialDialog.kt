package com.roman.kubik.songer.presentation.tutorial

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import butterknife.ButterKnife
import butterknife.OnClick
import com.roman.kubik.songer.R
import kotlinx.android.synthetic.main.dialog_tutorial.*

class TutorialDialog : DialogFragment() {

    private lateinit var type: TutorialType
    var dismissListener: DismissListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.Theme_AppCompat)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setBackgroundDrawable(
                ColorDrawable(ContextCompat.getColor(context!!, R.color.tutorial_background)))
        return inflater.inflate(R.layout.dialog_tutorial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)
        initView()
    }

    private fun initView() {
        type = TutorialType.valueOf(arguments!![TUTORIAL_TYPE] as String)

        actionImage.setImageResource(type.logoId)
        description.text = getString(type.labelId)
    }

    @OnClick(R.id.gotItButton)
    fun onGotItClicked() {
        this.dismiss()
        dismissListener?.onDismissed(type)
    }

    interface DismissListener {
        fun onDismissed(tutorialType: TutorialType)
    }

    companion object {

        private const val TUTORIAL_TYPE = "tutorial_type"

        fun getInstance(type: TutorialType): TutorialDialog {
            val dialog = TutorialDialog()
            val args = Bundle()
            args.putString(TUTORIAL_TYPE, type.name)
            dialog.arguments = args
            return dialog
        }
    }

}