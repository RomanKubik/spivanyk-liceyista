package com.roman.kubik.songer.songs.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.roman.kubik.songer.core.ui.base.BaseFragment
import com.roman.kubik.songs.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_song_details.*
import java.lang.IllegalArgumentException

@AndroidEntryPoint
class SongDetailsFragment: BaseFragment() {

    companion object {
        const val ARG_SONG_ID = "songId"
    }

    private val viewModel: SongDetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_song_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObservables()
        viewModel.loadSong(arguments?.getString(ARG_SONG_ID) ?: throw IllegalArgumentException("songId was not passed as argument"))
    }

    private fun setupObservables() {
        viewModel.song.observe(viewLifecycleOwner, Observer {
            songTitle.text = it.title
            songLyrics.text = it.lyrics
        })
    }
}