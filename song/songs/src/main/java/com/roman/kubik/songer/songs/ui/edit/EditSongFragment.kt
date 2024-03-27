package com.roman.kubik.songer.songs.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.roman.kubik.songer.core.ui.base.BaseFragment
import com.roman.kubik.songer.core.ui.utils.hide
import com.roman.kubik.songer.core.ui.utils.show
import com.roman.kubik.songer.song.songs.R
import com.roman.kubik.songer.song.songs.databinding.FragmentEditSongBinding
import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.view.tutorial.TutorialDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditSongFragment: BaseFragment<FragmentEditSongBinding>() {

    private val viewModel: EditSongViewModel by viewModels()

    companion object {
        const val ARG_SONG_ID = "songId"
    }

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentEditSongBinding {
        return FragmentEditSongBinding.inflate(inflater, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        setupToolbar(binding.editSongToolbar)
        setupOptionsMenu()
        setupObservables()
        arguments?.getString(ARG_SONG_ID).let(viewModel::loadSong)
    }

    private fun setupOptionsMenu() {
        binding.apply {
            editSongToolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.save -> viewModel.save(
                        editSongTitle.text.toString(),
                        editSongLyrics.text.toString()
                    )

                    R.id.recognize -> viewModel.recognize(
                        editSongTitle.text.toString(),
                        editSongLyrics.text.toString()
                    )
                }
                true
            }
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
        binding.progressBar.show()
    }

    private fun showEmptyTitle() {
        binding.progressBar.hide()
        Toast.makeText(requireContext(), R.string.error_save_song_empty_title, Toast.LENGTH_SHORT).show()
    }

    private fun showEmptyLyrics() {
        binding.progressBar.hide()
        Toast.makeText(requireContext(), R.string.error_save_song_empty_lyrics, Toast.LENGTH_SHORT).show()
    }

    private fun showAddNewSong() {
        binding.progressBar.hide()
    }

    private fun showError() {
        binding.progressBar.hide()
        Toast.makeText(requireContext(), R.string.error_save_song_general, Toast.LENGTH_SHORT).show()
    }

    private fun showSong(song: Song) {
        binding.progressBar.hide()
        binding.editSongTitle.setText(song.title)
        binding.editSongLyrics.setText(song.lyrics)
    }
}