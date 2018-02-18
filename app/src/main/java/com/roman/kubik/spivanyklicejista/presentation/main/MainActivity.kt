package com.roman.kubik.spivanyklicejista.presentation.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.annimon.stream.function.Consumer
import com.roman.kubik.spivanyklicejista.R
import com.roman.kubik.spivanyklicejista.domain.song.Song
import com.roman.kubik.spivanyklicejista.general.android.SpivanykApplication.Companion.component
import com.roman.kubik.spivanyklicejista.presentation.BaseActivity
import com.roman.kubik.spivanyklicejista.presentation.main.di.MainModule
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Main activity
 * Created by kubik on 1/14/18.
 */

class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter
    @Inject
    lateinit var songsAdapter: SongsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.mainComponent(MainModule(this)).inject(this)
        init()
        presenter.fetchAllSongs()
    }

    override fun showProgress(show: Boolean) {
        Log.d(TAG, "showProgress: " + show)
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showError(errorMessage: String?) {
        Log.d(TAG, "showError: " + errorMessage)
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onSongsFetched(songList: List<Song>) {
        Log.d(TAG, "onSongsFetched: " + songList.size)
        songsAdapter.addSongList(songList)
    }

    private fun init() {
        songsAdapter.setOnClickListener(Consumer { s -> Log.d(TAG, "songClicked: " + s.title)  })
        songList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        songList.adapter = songsAdapter
    }

    companion object {

        private val TAG = MainActivity::class.java.simpleName
    }
}
