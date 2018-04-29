package com.roman.kubik.spivanyklicejista.presentation.edit

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
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

    override fun onBackPressed() {
        showSaveDialog()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit_song, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.app_bar_recognize -> presenter.recognizeChords(lyrics.text.toString())
            R.id.app_bar_save -> presenter.saveSong(songTitle.text.toString(), lyrics.text.toString())
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showSong(song: Song) {
        songTitle.setText(song.title)
        lyrics.setText(song.lyrics)
    }

    override fun onChordsRecognized(lyrics: String) {
        this.lyrics.setText(lyrics)
    }

    override fun onSongSaved() {
        setResult(Activity.RESULT_OK)
        finish()
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
        supportActionBar?.setDisplayShowTitleEnabled(false)
        presenter.fetchSong(intent.getIntExtra(Constants.Extras.SONG_ID, -1))
    }

    private fun showSaveDialog() {
        AlertDialog.Builder(this)
                .setTitle(R.string.ttl_save_changes)
                .setMessage(R.string.msg_save_changes)
                .setPositiveButton(R.string.save, { _, _ ->
                    presenter.saveSong(songTitle.text.toString(), lyrics.text.toString())
                })
                .setNegativeButton(R.string.discard, { _, _ ->
                    setResult(Activity.RESULT_CANCELED)
                    finish()
                })
                .show()
    }
}
