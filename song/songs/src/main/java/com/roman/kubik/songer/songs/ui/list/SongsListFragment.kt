package com.roman.kubik.songer.songs.ui.list

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman.kubik.settings.domain.preference.Preferences
import com.roman.kubik.songer.core.ui.base.search.BaseSearchFragment
import com.roman.kubik.songer.core.ui.utils.getAttributeColor
import com.roman.kubik.songer.core.ui.utils.hide
import com.roman.kubik.songer.core.ui.utils.show
import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songs.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_song_list.*
import kotlinx.android.synthetic.main.include_trouble.*
import kotlinx.android.synthetic.main.include_trouble.view.*

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
        load()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_song_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar(songsToobar)
        setupSongsList()
        setupObservables()
        troubleRetry.setOnClickListener {
            load()
        }
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
        arguments?.putString(ARG_QUERY, query)
        viewModel.searchSongs(query)
    }

    private fun load() {
        if (arguments?.getString(ARG_QUERY).isNullOrEmpty()) {
            viewModel.loadSongs(arguments?.getString(ARG_CATEGORY))
        } else {
            searchSongs(arguments?.getString(ARG_QUERY) ?: "")
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