package com.roman.kubik.spivanyklicejista.presentation.song

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.method.LinkMovementMethod
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.annimon.stream.function.Consumer
import com.roman.kubik.spivanyklicejista.Constants
import com.roman.kubik.spivanyklicejista.R
import com.roman.kubik.spivanyklicejista.domain.category.Category
import com.roman.kubik.spivanyklicejista.domain.chord.Chord
import com.roman.kubik.spivanyklicejista.domain.song.Song
import com.roman.kubik.spivanyklicejista.general.android.SpivanykApplication
import com.roman.kubik.spivanyklicejista.presentation.BaseActivity
import com.roman.kubik.spivanyklicejista.presentation.Navigate
import com.roman.kubik.spivanyklicejista.presentation.song.di.SongModule
import com.roman.kubik.spivanyklicejista.presentation.view.ChordDialog
import com.roman.kubik.spivanyklicejista.utils.AssetsDrawableLoader
import com.roman.kubik.spivanyklicejista.utils.CategoryTitleMapper
import kotlinx.android.synthetic.main.activity_song.*
import javax.inject.Inject


/**
 * Created by kubik on 1/20/18.
 */

class SongActivity : BaseActivity(), SongContract.View {

    private lateinit var bookmarkItem: MenuItem
    private lateinit var showChordsItem: MenuItem
    private lateinit var chordDialog: ChordDialog

    @Inject
    lateinit var presenter: SongContract.Presenter
    @Inject
    lateinit var chordsAdapter: ChordsListAdapter
    @Inject
    lateinit var assetsDrawableLoader: AssetsDrawableLoader
    @Inject
    lateinit var categoryTitleMapper: CategoryTitleMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)
        SpivanykApplication.component.songComponent(SongModule(this)).inject(this)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_song, menu)
        bookmarkItem = menu?.findItem(R.id.app_bar_bookmark)!!
        showChordsItem = menu.findItem(R.id.app_bar_show_chords)
        presenter.fetchPreferences()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.app_bar_bookmark -> presenter.addToFavourite()
            R.id.app_bar_share -> presenter.shareSong()
            R.id.app_bar_edit -> presenter.edit()
            R.id.app_bar_show_chords -> presenter.showChords()
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Constants.RequestCodes.EDIT_SONG && resultCode == Activity.RESULT_OK) {
            presenter.fetchSong(intent.getIntExtra(Constants.Extras.SONG_ID, 0))
        }
    }

    override fun onDestroy() {
        presenter.detroy()
        super.onDestroy()
    }

    override fun setSongTitle(title: CharSequence) {
        songTitle.text = title
    }

    override fun setSongLyrics(lyrics: CharSequence) {
        this.lyrics.text = lyrics
    }

    override fun setSongCategory(category: Category) {
        categoryView.text = categoryTitleMapper.getCategoryTitle(category.id)
    }

    override fun setSongDifficulty(difficulty: CharSequence) {
        difficultyView.text = difficulty
    }

    override fun isFavouriteSong(isFavourite: Boolean) {
        bookmarkItem.setIcon(if (isFavourite) R.drawable.ic_bookmark_black_24dp else R.drawable.ic_bookmark_border_black_24dp)
    }

    override fun setSongChords(chords: List<Chord>) {
        chordsAdapter.setChords(chords)
        chordDialog = ChordDialog(this, chords, assetsDrawableLoader)
    }

    override fun setChordsVisibility(visible: Boolean) {
        showChordsItem.isChecked = visible
        chordsList.visibility = if (visible) View.VISIBLE else View.GONE
        chords.visibility = if (visible) View.VISIBLE else View.GONE
        presenter.fetchSong(intent.getIntExtra(Constants.Extras.SONG_ID, 0))
    }

    override fun showChord(chord: String) {
        chordDialog.showActiveChordName(chord)
    }

    override fun share(type: String, title: String, lyrics: String) {
        val intent = Intent()
                .setAction(Intent.ACTION_SEND)
                .setType(type)
                .putExtra(Intent.EXTRA_TITLE, title)
                .putExtra(Intent.EXTRA_TEXT, lyrics)
        startActivity(intent)
    }

    override fun edit(song: Song) {
        Navigate.toEditActivity(this, song)
    }

    override fun showProgress(show: Boolean) {
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(this, "showError: $errorMessage", Toast.LENGTH_SHORT).show()
    }

    private fun init() {
        presenter.setChordColors(Color.BLACK, resources.getColor(R.color.transparent_grey))
        lyrics.movementMethod = LinkMovementMethod.getInstance()
        chordsAdapter.chordClickListener = Consumer { showChord(it.name) }
        chordsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        chordsList.adapter = chordsAdapter
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}