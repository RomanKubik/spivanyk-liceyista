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
import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.ui.SharedSongViewModel
import com.roman.kubik.songs.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_song_list.*
import kotlinx.android.synthetic.main.include_trouble.*
import kotlinx.android.synthetic.main.include_trouble.view.*

@AndroidEntryPoint
class SongsListFragment : BaseFragment() {

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (::savedView.isInitialized.not()) {
            savedView = inflater.inflate(R.layout.fragment_song_list, container, false)
        }
        return savedView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar(songsToolbar)
        initSearchView()
        setupSongsList()
        setupObservables()
        troubleRetry.setOnClickListener {
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
        val searchMenuItem = songsToolbar.menu.findItem(R.id.search)
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
        songsList.adapter = adapter
        songsList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        divider.setDrawable(ColorDrawable(requireContext().getAttributeColor(android.R.attr.textColorSecondary)))
        songsList.addItemDecoration(divider)
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
            if (it != null) {
                load()
                sharedViewModel.songDeletedCommand.clear()
            }
        }
    }

    private fun showLoading() {
        songsProgress.show()
        songsTrouble.hide()
    }

    private fun showEmptyList() {
        songsProgress.hide()
        songsTrouble.show()
        songsList.hide()
        songsTrouble.troubleRetry.hide()

        songsTrouble.troubleImage.setImageResource(R.drawable.ic_empty_list)
        songsTrouble.troubleTitle.setText(R.string.error_empty_list)
    }

    private fun showError() {
        songsProgress.hide()
        songsTrouble.show()
        songsList.hide()
        songsTrouble.troubleRetry.show()

        songsTrouble.troubleImage.setImageResource(R.drawable.ic_error)
        songsTrouble.troubleTitle.setText(R.string.error_load_list)
    }

    private fun showSuccess(songs: List<Song>, preferences: Preferences) {
        songsProgress.hide()
        songsTrouble.hide()
        songsList.show()

        adapter.showChords = preferences.showChords
        adapter.publishItems(songs)
    }
}