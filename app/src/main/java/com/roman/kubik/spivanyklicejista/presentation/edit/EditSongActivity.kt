package com.roman.kubik.spivanyklicejista.presentation.edit

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.roman.kubik.spivanyklicejista.Constants
import com.roman.kubik.spivanyklicejista.R
import com.roman.kubik.spivanyklicejista.domain.song.Song
import com.roman.kubik.spivanyklicejista.general.android.SpivanykApplication.Companion.component
import com.roman.kubik.spivanyklicejista.presentation.BaseActivity
import com.roman.kubik.spivanyklicejista.presentation.edit.di.EditSongModule
import kotlinx.android.synthetic.main.activity_edit_song.*
import javax.inject.Inject

class EditSongActivity : BaseActivity(), EditSongContract.View {

    @Inject
    lateinit var presenter: EditSongContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_song)
        component.editSongComponent(EditSongModule(this)).inject(this)
        init()
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showSong(song: Song) {
        songTitle.setText(song.title)
        lyrics.setText(song.lyrics)
    }

    override fun showProgress(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showError(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun init() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.fetchSong(intent.getIntExtra(Constants.Extras.SONG_ID, -1))
    }
}
