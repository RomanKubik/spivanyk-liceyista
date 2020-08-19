package com.roman.kubik.songer.songs.ui.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman.kubik.songer.core.ui.base.search.BaseSearchFragment
import com.roman.kubik.songs.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_song_list.*

@AndroidEntryPoint
class SongsListFragment: BaseSearchFragment() {

    companion object {
        const val ARG_CATEGORY = "category"
        const val ARG_QUERY = "query"
    }

    override val viewModel by viewModels<SongsListViewModel>()
    private lateinit var adapter: SongsListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_song_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar(songsToobar)
        adapter = SongsListAdapter(viewModel::selectSong)
        songsList.adapter = adapter
        songsList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        setupObservables()
        if (arguments?.getString(ARG_QUERY).isNullOrEmpty()) {
            viewModel.loadSongs(arguments?.getString(ARG_CATEGORY))
        } else {
            searchSongs(arguments?.getString(ARG_QUERY) ?: "")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        if (arguments?.getString(ARG_QUERY) != null) {
            searchMenuItem.expandActionView()
        }
    }

    fun searchSongs(query: String) {
        viewModel.searchSongs(query)
    }

    private fun setupObservables() {
        viewModel.preferences.observe(viewLifecycleOwner, Observer { adapter.showChords = it.showChords })
        viewModel.songs.observe(viewLifecycleOwner, Observer(adapter::publishItems))
    }
}