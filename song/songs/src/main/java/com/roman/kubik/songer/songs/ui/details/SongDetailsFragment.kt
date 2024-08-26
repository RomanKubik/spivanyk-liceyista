package com.roman.kubik.songer.songs.ui.details

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman.kubik.settings.domain.preference.Instrument
import com.roman.kubik.songer.chords.model.Chord
import com.roman.kubik.songer.core.ui.base.FragmentScrollListener
import com.roman.kubik.songer.core.ui.base.search.BaseSearchFragment
import com.roman.kubik.songer.core.ui.utils.hide
import com.roman.kubik.songer.core.ui.utils.show
import com.roman.kubik.songer.song.songs.R
import com.roman.kubik.songer.song.songs.databinding.FragmentSongDetailsBinding
import com.roman.kubik.songer.songs.domain.song.SongCategory
import com.roman.kubik.songer.songs.ui.SharedSongViewModel
import com.roman.kubik.songer.songs.ui.utils.toUiCategory
import com.roman.kubik.songer.songs.ui.view.ChordClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SongDetailsFragment : BaseSearchFragment<FragmentSongDetailsBinding>(), ChordClickListener {

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

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSongDetailsBinding {
        return FragmentSongDetailsBinding.inflate(inflater, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar(binding.songDetailsToolbar)
        setupMenuItems()
        setupChordsRecyclerView()
        setupScrollListener()
        setupTonalityListeners()
        setupObservables()
        loadSong()
        binding.songDetailsTrouble.troubleRetry.setOnClickListener {
            loadSong()
        }
    }

    override fun onStart() {
        super.onStart()
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    override fun onStop() {
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        super.onStop()
    }

    override fun onDestroy() {
        scrollListener?.onScrollUp()
        super.onDestroy()
    }

    private fun setupMenuItems() {
        binding.songDetailsToolbar.apply {
            bookmarkItem = menu.findItem(R.id.addToFavourite)
            deleteItem = menu.findItem(R.id.delete)
            tonalityItem = menu.findItem(R.id.tonality)

            setOnMenuItemClickListener {
                when (it.itemId) {
                    com.roman.kubik.songer.core.R.id.search -> viewModel.openSearch()
                    R.id.edit -> viewModel.editSong()
                    R.id.delete -> requestDeleteSong()
                    R.id.addToFavourite -> viewModel.likeDislikeSong()
                    R.id.share -> viewModel.shareSong()
                    R.id.tonality -> binding.transpositionContainer.isVisible =
                        !binding.transpositionContainer.isVisible
                }
                true
            }
        }
    }

    private fun loadSong() {
        viewModel.loadSong(
            arguments?.getString(ARG_SONG_ID)
                ?: throw IllegalArgumentException("songId was not passed as argument")
        )
    }

    private fun setupChordsRecyclerView() {
        binding.chordsList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.chordsList.adapter = chordsAdapter
    }


    private fun setupScrollListener() {
        scrollListener = activity as? FragmentScrollListener
        binding.songDetailsContainer.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY - oldScrollY <= 0) {
                scrollListener?.onScrollUp()
            } else {
                scrollListener?.onScrollDown()
            }
        })
    }

    private fun setupObservables() {
        binding.songLyrics.chordsClickListener = this
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
        binding.songDetailsProgress.show()
        binding.songDetailsContainer.hide()
        binding.songDetailsTrouble.root.hide()
    }

    private fun showError() {
        binding.songDetailsProgress.hide()
        binding.songDetailsContainer.hide()
        binding.songDetailsTrouble.root.show()

        binding.songDetailsTrouble.troubleTitle.setText(R.string.error_song_details)
        binding.songDetailsTrouble.troubleImage.setImageResource(R.drawable.ic_campfire)
    }

    private fun showSuccess(successState: SuccessState) {
        binding.songDetailsProgress.hide()
        binding.songDetailsContainer.show()
        binding.songDetailsTrouble.root.hide()

        binding.songLyrics.showChords = successState.preferences.showChords
        chordsAdapter.selectedInstrument = successState.preferences.selectedInstrument

        val song = successState.song
        binding.songTitle.text = song.title
        binding.songLyrics.text = song.lyrics
        binding.songCategory.setText(song.category.toUiCategory())
        chords = successState.chords.toList()
        binding.chordsList.isVisible = chords.isNotEmpty() && successState.preferences.showChords
        binding.chordsCaption.isVisible = chords.isNotEmpty() && successState.preferences.showChords
        chordsAdapter.publishItems(chords)
        bookmarkItem?.setIcon(if (song.isFavourite) R.drawable.ic_is_favourite else R.drawable.ic_is_not_favourite)
        deleteItem?.isVisible = song.category != SongCategory.WEB
        tonalityItem?.isVisible =
            successState.preferences.showChords && successState.chords.isNotEmpty()
    }

    private fun showSongDeletedSuccess() {
        sharedViewModel.songDeletedCommand.postValue(Unit)
    }

    private fun setupTonalityListeners() {
        binding.transpositionUp.setOnClickListener {
            viewModel.transpositionUp()
        }
        binding.transpositionDown.setOnClickListener {
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
            R.id.tonality -> binding.transpositionContainer.isVisible =
                !binding.transpositionContainer.isVisible
        }
        return true
    }

    private fun requestDeleteSong() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.dialog_remove_song))
            .setMessage(getString(R.string.dialog_remove_song_text))
            .setPositiveButton(getString(R.string.dialog_remove)) { _, _ ->
                viewModel.deleteSong()
            }
            .setNegativeButton(getString(com.roman.kubik.songer.core.R.string.cancel)) { _, _ ->
            }
            .show()
    }

    override fun onChordClicked(chordName: String) {
        ChordsDialog(
            requireContext(),
            chords,
            (viewModel.song.value as? SuccessState)?.preferences?.selectedInstrument
                ?: Instrument.GUITAR
        )
            .showChord(chordName)
    }
}