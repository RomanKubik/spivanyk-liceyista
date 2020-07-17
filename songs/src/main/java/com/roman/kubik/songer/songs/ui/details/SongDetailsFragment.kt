package com.roman.kubik.songer.songs.ui.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.roman.kubik.songer.core.ui.base.search.BaseSearchFragment
import com.roman.kubik.songs.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_song_details.*

@AndroidEntryPoint
class SongDetailsFragment: BaseSearchFragment() {

    companion object {
        const val ARG_SONG_ID = "songId"
    }

    override val viewModel: SongDetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_song_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar(songDetailsToobar)
        setupObservables()
        viewModel.loadSong(arguments?.getString(ARG_SONG_ID) ?: throw IllegalArgumentException("songId was not passed as argument"))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_song_details, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit -> viewModel.editSong()
        }
        return true
    }

    private fun setupObservables() {
        viewModel.song.observe(viewLifecycleOwner, Observer {
            songTitle.text = it.title
            songLyrics.text = it.lyrics
        })
    }
}