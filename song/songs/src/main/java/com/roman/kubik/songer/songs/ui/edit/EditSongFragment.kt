package com.roman.kubik.songer.songs.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.roman.kubik.songer.core.ui.base.BaseFragment
import com.roman.kubik.songer.core.ui.utils.hide
import com.roman.kubik.songer.core.ui.utils.show
import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.view.tutorial.TutorialDialogFragment
import com.roman.kubik.songs.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_edit_song.*

@AndroidEntryPoint
class EditSongFragment: BaseFragment() {

    private val viewModel: EditSongViewModel by viewModels()

    companion object {
        const val ARG_SONG_ID = "songId"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_song, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        setupToolbar(editSongToolbar)
        setupOptionsMenu()
        setupObservables()
        arguments?.getString(ARG_SONG_ID).let(viewModel::loadSong)
    }

    private fun setupOptionsMenu() {
        editSongToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.save -> viewModel.save(editSongTitle.text.toString(), editSongLyrics.text.toString())
                R.id.recognize -> viewModel.recognize(editSongTitle.text.toString(), editSongLyrics.text.toString())
            }
            true
        }
    }

    private fun setupObservables() {
        viewModel.song.observe(viewLifecycleOwner) { state ->
            when (state) {
                LoadingState -> showLoading()
                EmptyTitle -> showEmptyTitle()
                EmptyLyrics -> showEmptyLyrics()
                NewSongState -> showAddNewSong()
                is ErrorState -> showError()
                is EditSongState -> showSong(state.song)
            }
        }
        viewModel.showRecognizerHintCommand.observe(viewLifecycleOwner) {
            TutorialDialogFragment
                    .getInstance(R.drawable.ic_recognize, R.string.edit_song_tutorial_recognizer)
                    .show(childFragmentManager, null)
        }
    }

    private fun showLoading() {
        progressBar.show()
    }

    private fun showEmptyTitle() {
        progressBar.hide()
        Toast.makeText(requireContext(), R.string.error_save_song_empty_title, Toast.LENGTH_SHORT).show()
    }

    private fun showEmptyLyrics() {
        progressBar.hide()
        Toast.makeText(requireContext(), R.string.error_save_song_empty_lyrics, Toast.LENGTH_SHORT).show()
    }

    private fun showAddNewSong() {
        progressBar.hide()
    }

    private fun showError() {
        progressBar.hide()
        Toast.makeText(requireContext(), R.string.error_save_song_general, Toast.LENGTH_SHORT).show()
    }

    private fun showSong(song: Song) {
        progressBar.hide()
        editSongTitle.setText(song.title)
        editSongLyrics.setText(song.lyrics)
    }
}