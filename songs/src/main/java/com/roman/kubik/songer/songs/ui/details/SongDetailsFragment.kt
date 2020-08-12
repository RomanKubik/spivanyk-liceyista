package com.roman.kubik.songer.songs.ui.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman.kubik.songer.chords.model.Chord
import com.roman.kubik.songer.core.ui.base.search.BaseSearchFragment
import com.roman.kubik.songer.songs.ui.view.ChordClickListener
import com.roman.kubik.songs.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_song_details.*

@AndroidEntryPoint
class SongDetailsFragment : BaseSearchFragment(), ChordClickListener {

    companion object {
        const val ARG_SONG_ID = "songId"
    }

    private lateinit var bookmarkItem: MenuItem

    private val chordsAdapter: SongChordsAdapter = SongChordsAdapter(this)
    private var chords: List<Chord> = emptyList()

    override val viewModel: SongDetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_song_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar(songDetailsToobar)
        setupChordsRecyclerView()
        setupObservables()
        viewModel.loadSong(arguments?.getString(ARG_SONG_ID)
                ?: throw IllegalArgumentException("songId was not passed as argument"))
    }

    private fun setupChordsRecyclerView() {
        chordsList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        chordsList.adapter = chordsAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_song_details, menu)
        bookmarkItem = menu.findItem(R.id.addToFavourite)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit -> viewModel.editSong()
            R.id.addToFavourite -> viewModel.likeDislikeSong()
        }
        return true
    }

    private fun setupObservables() {
        songLyrics.chordsClickListener = this
        viewModel.song.observe(viewLifecycleOwner, Observer {
            val song = it.song
            songTitle.text = song.title
            songLyrics.text = song.lyrics
            chords = it.chords.toList()
            chordsAdapter.publishItems(chords)
            bookmarkItem.setIcon(if (song.isFavourite) R.drawable.ic_is_favourite else R.drawable.ic_is_not_favourite)
        })
    }

    override fun onChordClicked(chordName: String) {
        ChordsDialog(requireContext(), chords).showChord(chordName)
    }
}