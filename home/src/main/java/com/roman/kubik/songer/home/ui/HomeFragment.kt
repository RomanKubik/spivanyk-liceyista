package com.roman.kubik.songer.home.ui

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman.kubik.songer.core.ui.base.search.BaseSearchFragment
import com.roman.kubik.songer.core.ui.utils.getAttributeColor
import com.roman.kubik.songer.home.R
import com.roman.kubik.songer.view.tutorial.TutorialDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : BaseSearchFragment() {

    override val viewModel by viewModels<HomeFragmentViewModel>()
    private lateinit var adapter: HomeCategoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar(homeToolbar)
        setupCategoriesList()
        addObservables()
    }

    private fun setupCategoriesList() {
        adapter = HomeCategoryAdapter(viewModel::onCategorySelected)
        homeCategoryList.layoutManager = LinearLayoutManager(requireContext())
        homeCategoryList.adapter = adapter
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        divider.setDrawable(ColorDrawable(requireContext().getAttributeColor(android.R.attr.textColorSecondary)))
        homeCategoryList.addItemDecoration(divider)
    }

    private fun addObservables() {
        viewModel.categories.observe(viewLifecycleOwner, Observer(adapter::publishItems))
        viewModel.showShakeHintCommand.observe(viewLifecycleOwner) {
            TutorialDialogFragment
                    .getInstance(R.drawable.ic_tutorial_shake, R.string.home_tutorial_shake_phone)
                    .show(childFragmentManager, null)
        }
    }
}