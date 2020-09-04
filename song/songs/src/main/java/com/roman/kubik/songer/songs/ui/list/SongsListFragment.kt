package com.roman.kubik.songer.songs.ui.list

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman.kubik.songer.core.ui.base.search.BaseSearchFragment
import com.roman.kubik.songer.core.ui.utils.getAttributeColor
import com.roman.kubik.songs.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_song_list.*

@AndroidEntryPoint
class SongsListFragment : BaseSearchFragment() {

    companion object {
        const val ARG_CATEGORY = "category"
        const val ARG_QUERY = "query"
    }

    override val viewModel by viewModels<SongsListViewModel>()
    private lateinit var adapter: SongsListAdapter
    private var query: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments?.getString(ARG_QUERY).isNullOrEmpty()) {
            viewModel.loadSongs(arguments?.getString(ARG_CATEGORY))
        } else {
            searchSongs(arguments?.getString(ARG_QUERY) ?: "")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_song_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar(songsToobar)
        setupSongsList()
        setupObservables()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        if (arguments?.getString(ARG_QUERY) != null) {
            searchMenuItem.expandActionView()
            searchView.setQuery(arguments?.getString(ARG_QUERY) ?: "", false)
        }
    }

    fun searchSongs(query: String) {
        this.query = query
        viewModel.searchSongs(query)
    }

    private fun setupSongsList() {
        adapter = SongsListAdapter {
            if (query != null) {
                arguments?.putString(ARG_QUERY, query)
            }
            viewModel.selectSong(it)
        }
        songsList.adapter = adapter
        songsList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        divider.setDrawable(ColorDrawable(requireContext().getAttributeColor(android.R.attr.textColorSecondary)))
        songsList.addItemDecoration(divider)
    }

    private fun setupObservables() {
        viewModel.preferences.observe(viewLifecycleOwner, { adapter.showChords = it.showChords })
        viewModel.songs.observe(viewLifecycleOwner, adapter::publishItems)
    }
}