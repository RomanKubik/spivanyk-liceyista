package com.roman.kubik.songer.ui.home

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman.kubik.songer.R
import com.roman.kubik.songer.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val viewModel by viewModels<HomeFragmentViewModel>()
    private lateinit var adapter: HomeCategoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupCategoriesList()
        addObservables()
    }

    private fun setupCategoriesList() {
        adapter = HomeCategoryAdapter(viewModel::onCategorySelected)
        homeCategoryList.layoutManager = LinearLayoutManager(requireContext())
        homeCategoryList.adapter = adapter
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        divider.setDrawable(ColorDrawable(ContextCompat.getColor(requireContext(), R.color.colorAccent)))
        homeCategoryList.addItemDecoration(divider)
    }

    private fun addObservables() {
        viewModel.categories.observe(viewLifecycleOwner, Observer(adapter::publishItems))

    }
}