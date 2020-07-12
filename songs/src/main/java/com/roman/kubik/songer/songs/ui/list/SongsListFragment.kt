package com.roman.kubik.songer.songs.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman.kubik.songer.core.ui.base.BaseFragment
import com.roman.kubik.songs.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_song_list.*

@AndroidEntryPoint
class SongsListFragment: BaseFragment() {

    companion object {
        const val ARG_CATEGORY = "category"
    }

    private val viewModel by viewModels<SongsListViewModel>()
    private lateinit var adapter: SongsListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_song_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = SongsListAdapter(viewModel::selectSong)
        songsList.adapter = adapter
        songsList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        setupObservables()
        viewModel.loadSongs(arguments?.getString(ARG_CATEGORY))
    }

    private fun setupObservables() {
        viewModel.songs.observe(viewLifecycleOwner, Observer(adapter::publishItems))
    }
}