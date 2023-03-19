package com.roman.kubik.songer.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.roman.kubik.songer.core.ui.base.search.BaseSearchFragment
import com.roman.kubik.songer.home.R
import com.roman.kubik.songer.view.tutorial.TutorialDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : BaseSearchFragment() {

    override val viewModel by viewModels<HomeFragmentViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                HomeScreen()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservables()
    }

    private fun addObservables() {
        viewModel.showHintsCommand.observe(viewLifecycleOwner, Observer(this::showHint))
    }

    private fun showHint(hints: Queue<HintType>) {
        if (hints.isEmpty()) return

        val hint = hints.poll()!!
        val image: Int
        val text: Int

        when (hint) {
            HintType.SHAKE_PHONE -> {
                image = R.drawable.ic_tutorial_shake
                text = R.string.home_tutorial_shake_phone
            }
            HintType.SUPPORT_DEVELOPER -> {
                image = R.drawable.ic_tutorial_support_developer
                text = R.string.home_tutorial_support_developer
            }
            HintType.DERUSSIFICATION -> {
                image = R.drawable.ic_ukraine
                text = R.string.home_tutorial_derussification
            }
        }
        TutorialDialogFragment
                .getInstance(image, text).let {
                    it.dismissListener = object : TutorialDialogFragment.DismissListener {
                        override fun onDismissed(tag: String?) {
                            showHint(hints)
                            viewModel.hintDismissed(hint)
                        }
                    }
                    it.show(childFragmentManager, null)
                }

    }
}