package com.roman.kubik.songer.home.ui

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman.kubik.songer.core.ui.base.search.BaseSearchFragment
import com.roman.kubik.songer.core.ui.utils.getAttributeColor
import com.roman.kubik.songer.home.R
import com.roman.kubik.songer.home.databinding.FragmentHomeBinding
import com.roman.kubik.songer.view.tutorial.TutorialDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : BaseSearchFragment<FragmentHomeBinding>() {

    override val viewModel by viewModels<HomeFragmentViewModel>()
    private lateinit var adapter: HomeCategoryAdapter
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MyTag", "HomeFragment")
        setupToolbar(binding.homeToolbar)
        setupCategoriesList()
        addObservables()
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyTag", "HomeFragment: onResume: ${binding.homeCategoryList.isVisible}")
    }

    private fun setupCategoriesList() {
        adapter = HomeCategoryAdapter(viewModel::onCategorySelected)
        binding.homeCategoryList.layoutManager = LinearLayoutManager(requireContext())
        binding.homeCategoryList.adapter = adapter
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        divider.setDrawable(ColorDrawable(requireContext().getAttributeColor(android.R.attr.textColorSecondary)))
        binding.homeCategoryList.addItemDecoration(divider)
    }

    private fun addObservables() {
        viewModel.categories.observe(viewLifecycleOwner, Observer(adapter::publishItems))
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