package com.roman.kubik.songer.songs.ui.details

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.roman.kubik.settings.domain.preference.Instrument
import com.roman.kubik.songer.chords.model.Chord
import com.roman.kubik.songer.core.ui.base.search.BaseSearchFragment
import com.roman.kubik.songer.songs.ui.utils.toUiCategory
import com.roman.kubik.songer.songs.ui.view.ChordClickListener
import com.roman.kubik.songs.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_song_details.*

@AndroidEntryPoint
class SongDetailsFragment : BaseSearchFragment(), ChordClickListener {

    companion object {
        const val ARG_SONG_ID = "songId"
    }

    private var bookmarkItem: MenuItem? = null

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
        setupTonalityListeners()
        setupObservables()
        viewModel.loadSong(arguments?.getString(ARG_SONG_ID)
                ?: throw IllegalArgumentException("songId was not passed as argument"))
    }

    private fun setupChordsRecyclerView() {
        chordsList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        chordsList.adapter = chordsAdapter
    }

    private fun setupObservables() {
        songLyrics.chordsClickListener = this
        viewModel.preferences.observe(viewLifecycleOwner, {
            chordsList.isVisible = it.showChords
            songLyrics.showChords = it.showChords
            chordsAdapter.selectedInstrument = it.selectedInstrument
        })
        viewModel.song.observe(viewLifecycleOwner, {
            val song = it.song
            songTitle.text = song.title
            songLyrics.text = song.lyrics
            songCategory.setText(song.category.toUiCategory())
            chords = it.chords.toList()
            chordsAdapter.publishItems(chords)
            bookmarkItem?.setIcon(if (song.isFavourite) R.drawable.ic_is_favourite else R.drawable.ic_is_not_favourite)
        })
    }

    private fun setupTonalityListeners() {
        transpositionUp.setOnClickListener {
            viewModel.transpositionUp()
        }
        transpositionDown.setOnClickListener {
            viewModel.transpositionDown()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_song_details, menu)
        bookmarkItem = menu.findItem(R.id.addToFavourite)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit -> viewModel.editSong()
            R.id.delete -> requestDeleteSong()
            R.id.addToFavourite -> viewModel.likeDislikeSong()
            R.id.share -> viewModel.shareSong()
            R.id.tonality -> transpositionContainer.isVisible = !transpositionContainer.isVisible
        }
        return true
    }

    private fun requestDeleteSong() {
        MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.dialog_remove_song))
                .setMessage(getString(R.string.dialog_remove_song_text))
                .setPositiveButton(getString(R.string.dialog_remove)) { _, _ ->
                    viewModel.deleteSong()
                }
                .setNegativeButton(getString(R.string.cancel)) { _, _ ->
                }
                .show()
    }

    override fun onChordClicked(chordName: String) {
        ChordsDialog(requireContext(),
                chords,
                viewModel.preferences.value?.selectedInstrument ?: Instrument.GUITAR)
                .showChord(chordName)
    }
}