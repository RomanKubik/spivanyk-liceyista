package com.roman.kubik.songer.songs.ui.details

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.roman.kubik.settings.domain.preference.Instrument
import com.roman.kubik.songer.chords.model.Chord
import com.roman.kubik.songer.core.ui.base.FragmentScrollListener
import com.roman.kubik.songer.core.ui.base.search.BaseSearchFragment
import com.roman.kubik.songer.core.ui.utils.hide
import com.roman.kubik.songer.core.ui.utils.show
import com.roman.kubik.songer.songs.domain.song.SongCategory
import com.roman.kubik.songer.songs.ui.SharedSongViewModel
import com.roman.kubik.songer.songs.ui.utils.toUiCategory
import com.roman.kubik.songer.songs.ui.view.ChordClickListener
import com.roman.kubik.songer.view.tutorial.TutorialDialogFragment
import com.roman.kubik.songs.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_song_details.*
import kotlinx.android.synthetic.main.include_trouble.*

@AndroidEntryPoint
class SongDetailsFragment : BaseSearchFragment(), ChordClickListener {

    companion object {
        const val ARG_SONG_ID = "songId"
    }

    private var bookmarkItem: MenuItem? = null
    private var deleteItem: MenuItem? = null
    private var tonalityItem: MenuItem? = null

    private val chordsAdapter: SongChordsAdapter = SongChordsAdapter(this)
    private var chords: List<Chord> = emptyList()

    override val viewModel: SongDetailsViewModel by viewModels()
    private val sharedViewModel by activityViewModels<SharedSongViewModel>()

    private var scrollListener: FragmentScrollListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_song_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar(songDetailsToolbar)
        setupMenuItems()
        setupChordsRecyclerView()
        setupScrollListener()
        setupTonalityListeners()
        setupObservables()
        loadSong()
        troubleRetry.setOnClickListener {
            loadSong()
        }
    }

    override fun onDestroy() {
        scrollListener?.onScrollUp()
        super.onDestroy()
    }

    private fun setupMenuItems() {
        songDetailsToolbar.apply {
            bookmarkItem = menu.findItem(R.id.addToFavourite)
            deleteItem = menu.findItem(R.id.delete)
            tonalityItem = menu.findItem(R.id.tonality)

            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.search -> viewModel.openSearch()
                    R.id.edit -> viewModel.editSong()
                    R.id.delete -> requestDeleteSong()
                    R.id.addToFavourite -> viewModel.likeDislikeSong()
                    R.id.share -> viewModel.shareSong()
                    R.id.tonality -> transpositionContainer.isVisible = !transpositionContainer.isVisible
                }
                true
            }
        }
    }

    private fun loadSong() {
        viewModel.loadSong(arguments?.getString(ARG_SONG_ID)
                ?: throw IllegalArgumentException("songId was not passed as argument"))
    }

    private fun setupChordsRecyclerView() {
        chordsList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        chordsList.adapter = chordsAdapter
    }


    private fun setupScrollListener() {
        scrollListener = activity as? FragmentScrollListener
        songDetailsContainer.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY - oldScrollY <= 0) {
                scrollListener?.onScrollUp()
            } else {
                scrollListener?.onScrollDown()
            }
        })
    }

    private fun setupObservables() {
        songLyrics.chordsClickListener = this
        viewModel.song.observe(viewLifecycleOwner) {
            when (it) {
                LoadingState -> showLoading()
                is ErrorState -> showError()
                is SuccessState -> showSuccess(it)
                SongDeleteSuccess -> showSongDeletedSuccess()
            }
        }
    }

    private fun showLoading() {
        songDetailsProgress.show()
        songDetailsContainer.hide()
        songDetailsTrouble.hide()
    }

    private fun showError() {
        songDetailsProgress.hide()
        songDetailsContainer.hide()
        songDetailsTrouble.show()

        troubleTitle.setText(R.string.error_song_details)
        troubleImage.setImageResource(R.drawable.ic_campfire)
    }

    private fun showSuccess(successState: SuccessState) {
        songDetailsProgress.hide()
        songDetailsContainer.show()
        songDetailsTrouble.hide()

        chordsList.isVisible = successState.preferences.showChords
        songLyrics.showChords = successState.preferences.showChords
        chordsAdapter.selectedInstrument = successState.preferences.selectedInstrument

        val song = successState.song
        songTitle.text = song.title
        songLyrics.text = song.lyrics
        songCategory.setText(song.category.toUiCategory())
        chords = successState.chords.toList()
        chordsList.isVisible = chords.isNotEmpty()
        chordsCaption.isVisible = chords.isNotEmpty()
        chordsAdapter.publishItems(chords)
        bookmarkItem?.setIcon(if (song.isFavourite) R.drawable.ic_is_favourite else R.drawable.ic_is_not_favourite)
        deleteItem?.isVisible = song.category != SongCategory.WEB
        tonalityItem?.isVisible = successState.preferences.showChords && successState.chords.isNotEmpty()
    }

    private fun showSongDeletedSuccess() {
        sharedViewModel.songDeletedCommand.postValue(Unit)
    }

    private fun setupTonalityListeners() {
        transpositionUp.setOnClickListener {
            viewModel.transpositionUp()

            val fragment = TutorialDialogFragment.getInstance(R.drawable.ic_search, R.string.dialog_remove_song_text)
            fragment.dismissListener = object : TutorialDialogFragment.DismissListener {
                override fun onDismissed(tag: String?) {
                    Toast.makeText(requireContext(), "Dismissed : $tag", Toast.LENGTH_SHORT).show()
                }

            }
            childFragmentManager.beginTransaction().add(fragment, "MyTag")
                    .commit()
        }
        transpositionDown.setOnClickListener {
            viewModel.transpositionDown()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_song_details, menu)
        bookmarkItem = menu.findItem(R.id.addToFavourite)
        deleteItem = menu.findItem(R.id.delete)
        tonalityItem = menu.findItem(R.id.tonality)
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
                (viewModel.song.value as? SuccessState)?.preferences?.selectedInstrument
                        ?: Instrument.GUITAR)
                .showChord(chordName)
    }
}