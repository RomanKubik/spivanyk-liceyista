package com.roman.kubik.songer.songs.ui.list

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman.kubik.settings.domain.preference.Preferences
import com.roman.kubik.songer.core.ui.base.BaseFragment
import com.roman.kubik.songer.core.ui.utils.getAttributeColor
import com.roman.kubik.songer.core.ui.utils.hide
import com.roman.kubik.songer.core.ui.utils.show
import com.roman.kubik.songer.song.songs.R
import com.roman.kubik.songer.song.songs.databinding.FragmentSongListBinding
import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.ui.SharedSongViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SongsListFragment : BaseFragment<FragmentSongListBinding>() {

    companion object {
        const val ARG_CATEGORY = "category"
        const val ARG_QUERY = "query"
    }

    private val viewModel by viewModels<SongsListViewModel>()
    private val sharedViewModel by activityViewModels<SharedSongViewModel>()
    private lateinit var adapter: SongsListAdapter
    private lateinit var savedView: View
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        load()
    }

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSongListBinding {
        return FragmentSongListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar(binding.songsToolbar)
        initSearchView()
        setupSongsList()
        setupObservables()
        binding.songsTrouble.troubleRetry.setOnClickListener {
            load()
        }
    }

    private fun load() {
        if (searchView?.query.isNullOrBlank()) {
            viewModel.loadSongs(arguments?.getString(ARG_CATEGORY))
        } else {
            viewModel.searchSongs(searchView?.query?.toString() ?: "")
        }
    }

    private fun initSearchView() {
        if (searchView != null) return
        val searchMenuItem = binding.songsToolbar.menu.findItem(com.roman.kubik.songer.core.R.id.search)
        searchView = searchMenuItem.actionView as SearchView
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (query != null) {
                    viewModel.searchSongs(query)
                    true
                } else {
                    false
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return if (newText != null) {
                    viewModel.searchSongs(newText)
                    true
                } else {
                    false
                }
            }

        })
        arguments?.getString(ARG_QUERY)?.let {
            searchMenuItem.expandActionView()
            searchView?.setQuery(it, true)
        }
    }

    private fun setupSongsList() {
        adapter = SongsListAdapter {
            viewModel.selectSong(it)
        }
        binding.songsList.adapter = adapter
        binding.songsList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        divider.setDrawable(ColorDrawable(requireContext().getAttributeColor(android.R.attr.textColorSecondary)))
        binding.songsList.addItemDecoration(divider)
    }

    private fun setupObservables() {
        viewModel.songs.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                LoadingState -> showLoading()
                NoSongsViewState -> showEmptyList()
                is ErrorState -> showError()
                is SuccessState -> showSuccess(viewState.songs, viewState.preferences)
            }
        }
        sharedViewModel.songDeletedCommand.observe(viewLifecycleOwner) {
            load()
        }
    }

    private fun showLoading() {
        binding.apply {
            songsProgress.show()
            songsTrouble.root.hide()
        }
    }

    private fun showEmptyList() {
        binding.apply {
            songsProgress.hide()
            songsTrouble.root.show()
            songsList.hide()
            songsTrouble.troubleRetry.hide()

            songsTrouble.troubleImage.setImageResource(R.drawable.ic_empty_list)
            songsTrouble.troubleTitle.setText(R.string.error_empty_list)
        }
    }

    private fun showError() {
        binding.apply {
            songsProgress.hide()
            songsTrouble.root.show()
            songsList.hide()
            songsTrouble.troubleRetry.show()

            songsTrouble.troubleImage.setImageResource(R.drawable.ic_error)
            songsTrouble.troubleTitle.setText(R.string.error_load_list)
        }
    }

    private fun showSuccess(songs: List<Song>, preferences: Preferences) {
        binding.apply {
            songsProgress.hide()
            songsTrouble.root.hide()
            songsList.show()
        }

        adapter.showChords = preferences.showChords
        adapter.publishItems(songs)
    }
}